package com.gebond.ip.math.func.image;

import static com.gebond.ip.math.commons.util.ImageUtil.normalizePixelArray;
import static com.gebond.ip.math.func.util.ProcessingUtil.buildTransformForType;
import static com.gebond.ip.model.converter.ConverterUtil.converRGBToYCrCb;
import static com.gebond.ip.model.converter.ConverterUtil.converYCrCbToRGB;
import static com.gebond.ip.model.metric.Metrics.MetricsType.MSE;
import static com.gebond.ip.model.metric.Metrics.MetricsType.PSNR;
import static com.gebond.ip.model.setting.ImageSetting.RGB.BLUE;
import static com.gebond.ip.model.setting.ImageSetting.RGB.GREEN;
import static com.gebond.ip.model.setting.ImageSetting.RGB.RED;
import static org.apache.commons.math3.util.FastMath.abs;
import static org.apache.commons.math3.util.FastMath.pow;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.image.ImageProcessing.ConsistentProcessingOperation.CalculateMetricsOperation;
import com.gebond.ip.math.func.image.ImageProcessing.ConsistentProcessingOperation.PostProcessingOperation;
import com.gebond.ip.math.func.image.ImageProcessing.ConsistentProcessingOperation.StopCounterOperation;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;
import com.gebond.ip.math.func.transform.DiscreteTransformation2D;
import com.gebond.ip.math.func.transform.HaartTransformation2D;
import com.gebond.ip.math.func.transform.WalshTransformation2D;
import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.Vector3D;
import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created on 17/02/18.
 */
public class ImageProcessing extends OperationManager<ImageContext> {

  @Override
  public List<Operation<ImageContext>> getOperations() {
    return Arrays.asList(
        new ValidationOperation(),
        new SplittingOperation(),
        new PreProcessingOperation(),
        new ConsistentProcessingOperation(),
        new ConcurrentOperation(),
        new PostProcessingOperation(),
        new BuildingOperation(),
        new CalculateMetricsOperation(),
        new StopCounterOperation()
    );
  }

  public static class ValidationOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      // no validations - validate always
      return true;
    }

    @Override
    public void apply(ImageContext context) {
      if (context.getResultSetting().getImageSetting() == null) {
        throw new IllegalArgumentException("Image setting is null!");
      }
      if (context.getResultSetting().getTransformSetting() == null) {
        throw new IllegalArgumentException("Transform setting is null!");
      }
      if (context.getResultSetting().getImageSetting().getImageSchema() == null) {
        throw new IllegalArgumentException("Image schema setting is null!");
      }
      if (context.getResultSetting().getImageSetting().getSourceImage() == null) {
        throw new IllegalArgumentException("Source image setting is null!");
      }
      if (context.getResultSetting().getImageSetting().getSegmentSize() == null) {
        throw new IllegalArgumentException("SegmentSize setting is null!");
      }
      if (context.getResultSetting().getImageSetting().getImageSchema().getAmount()
          != context.getResultSetting().getImageSetting().getCompressionValues().size()) {
        throw new IllegalArgumentException("Wrong imageSetting configuration");
      }
    }
  }

  public static class SplittingOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      if (context.getResultSetting().getImageSetting() == null
          || context.getResultSetting().getImageSetting().getSourceImage() == null) {
        throw new IllegalArgumentException("Image or setting is null");
      }
      if (context.getResultSetting().getImageSetting().getSegmentSize() == null) {
        throw new IllegalArgumentException("Segment size is null");
      }
      if (context.getResultSetting().getImageSetting().getSourceImage().getHeight() < context
          .getResultSetting().getImageSetting().getSegmentSize().getValue()
          || context.getResultSetting().getImageSetting().getSourceImage().getWidth() < context
          .getResultSetting().getImageSetting().getSegmentSize().getValue()) {
        throw new IllegalArgumentException("Height or Width must be more than segment size");
      }
      return true;
    }

    @Override
    public void apply(ImageContext context) {
      BufferedImage bufferedImage = context.getResultSetting().getImageSetting().getSourceImage();

      int size = context.getResultSetting().getImageSetting().getSegmentSize().getValue();
      context.setRowCount(bufferedImage.getHeight() / size);
      context.setColumnCount(bufferedImage.getWidth() / size);

      for (int i = 0; i < context.getRowCount(); i++) {
        for (int j = 0; j < context.getColumnCount(); j++) {
          context.getPixelList().add(buildVector(bufferedImage, i, j, size));
        }
      }
    }

    private Vector3D<Array2D> buildVector(BufferedImage bufferedImage, int rowCurrent,
        int columnCurrent, int size) {
      double[][] arrayReds = new double[size][size];
      double[][] arrayGreens = new double[size][size];
      double[][] arrayBlues = new double[size][size];
      // x -> column, y -> row
      for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
          Color color = new Color(
              bufferedImage.getRGB(size * columnCurrent + x, size * rowCurrent + y));
          arrayReds[x][y] = color.getRed();
          arrayGreens[x][y] = color.getGreen();
          arrayBlues[x][y] = color.getBlue();
        }
      }
      Vector3D<Array2D> vector = new Vector3D<>(
          new Array2D(arrayReds),
          new Array2D(arrayGreens),
          new Array2D(arrayBlues));
      return vector;
    }
  }

  public static class PreProcessingOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return context.getPixelList().size() > 0;
    }

    @Override
    public void apply(ImageContext context) {
      if (context.getResultSetting().getImageSetting().getImageSchema()
          .equals(ImageSetting.ImageSchema.YCRCB)) {
        for (Vector3D<Array2D> vector : context.getPixelList()) {
          converRGBToYCrCb(vector);
        }
      }
    }
  }

  public static class BuildingOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      if (context.getResultSetting().getImageSetting().getSegmentSize() == null) {
        throw new IllegalArgumentException("Image setting segment is null");
      }
      if (context.getPixelList() == null || context.getPixelList().isEmpty()) {
        throw new IllegalArgumentException("List of pixel vector arrays is null or empty");
      }
      if (context.getColumnCount() == 0 || context.getRowCount() == 0) {
        throw new IllegalArgumentException("Column/Row count is null");
      }
      return true;
    }

    @Override
    public void apply(ImageContext context) {
      BufferedImage newImage = new BufferedImage(
          context.getColumnCount() * context.getResultSetting().getImageSetting().getSegmentSize()
              .getValue(),
          context.getRowCount() * context.getResultSetting().getImageSetting().getSegmentSize()
              .getValue(),
          BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < context.getRowCount(); i++) {
        for (int j = 0; j < context.getColumnCount(); j++) {
          applyVector(
              newImage,
              context.getPixelList().get(i * context.getColumnCount() + j),
              i,
              j,
              context.getResultSetting().getImageSetting().getSegmentSize().getValue());
        }
      }
      context.getResultSetting().setResultImage(newImage);
    }

    private void applyVector(BufferedImage image, Vector3D<Array2D> vector, int rowCurrent,
        int columnCurrent, int size) {
      double[][] arrayReds = vector.getX().getArray2DCopy();
      double[][] arrayGreens = vector.getY().getArray2DCopy();
      double[][] arrayBlues = vector.getZ().getArray2DCopy();
      for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
          Color color = new Color(
              (int) arrayReds[x][y],
              (int) arrayGreens[x][y],
              (int) arrayBlues[x][y]);
          image.setRGB(size * columnCurrent + x, size * rowCurrent + y, color.getRGB());
        }
      }
    }
  }

  public static class ConcurrentOperation extends TransformationOperation {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return !context.IS_SINGLE_THREAD;
    }

    @Override
    public void apply(ImageContext context) {
      OperationManager<FourierContext.FourierContext2D> transformation2D = buildTransformForType(
          context.getResultSetting().getTransformSetting().getType());

      List<Vector<Array2D>> pixelList = context.getPixelList();
      List<CompletableFuture<Vector<Array2D>>> futures =
          pixelList.stream()
              .map(v -> CompletableFuture.supplyAsync(() -> applyAsync(v, transformation2D,
                  context.getResultSetting().getImageSetting().getCompressionValues())))
              .collect(Collectors.toList());
      List<Vector<Array2D>> result =
          futures.stream()
              .map(CompletableFuture::join)
              .collect(Collectors.toList());
      context.setPixelList(result);
    }

    private Vector<Array2D> applyAsync(Vector<Array2D> vector,
        OperationManager<FourierContext.FourierContext2D> transformation2D,
        Map<Integer, CompressionSetting> compressionSettingMap) {
      vector.setX(new Array2D(transformation2D
          .process(FourierContext.start2DBuilder(vector.getX().getArray2DCopy())
              .withCompression(compressionSettingMap.get(RED.getOrder()))
              .build())
          .getFourierData().getArray2DCopy()));
      vector.setY(new Array2D(transformation2D
          .process(FourierContext
              .start2DBuilder(vector.getY().getArray2DCopy())
              .withCompression(compressionSettingMap.get(GREEN.getOrder()))
              .build())
          .getFourierData().getArray2DCopy()));
      vector.setZ(new Array2D(transformation2D
          .process(FourierContext
              .start2DBuilder(vector.getZ().getArray2DCopy())
              .withCompression(compressionSettingMap.get(ImageSetting.RGB.BLUE.getOrder()))
              .build())
          .getFourierData().getArray2DCopy()));
      return vector;
    }
  }

  public static abstract class TransformationOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      if (context.getPixelList() == null || context.getPixelList().isEmpty()) {
        throw new IllegalArgumentException("Pixel array = null or empty");
      }
      if (context.getResultSetting().getTransformSetting().getType() == null) {
        throw new IllegalArgumentException("Transformation type is null");
      }
      return true;
    }
  }

  public static class ConsistentProcessingOperation extends TransformationOperation {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return context.IS_SINGLE_THREAD;
    }

    @Override
    public void apply(ImageContext context) {
      OperationManager<FourierContext.FourierContext2D> transformation2D = buildTransformForType(
          context.getResultSetting().getTransformSetting().getType());
      for (Vector3D<Array2D> vector : context.getPixelList()) {
      OperationManager<FourierContext.FourierContext2D> transformation2D;
      switch (context.getResultSetting().getTransformSetting().getType()) {
        case HAART_TRANSFORM:
          transformation2D = new HaartTransformation2D();
          break;
        case WALSH_TRANSFORM:
          transformation2D = new WalshTransformation2D();
          break;
        case DISCRETE_TRANSFORM:
          transformation2D = new DiscreteTransformation2D();
          break;
        default:
          transformation2D = new HaartTransformation2D();
      }
      for (Vector3D<Array2D> vector : context.getPixelList()) {
        // TODO change hardcoded vector size to generic. Should depend on Schema.amount
        vector.setX(new Array2D(transformation2D
            .process(FourierContext.start2DBuilder(vector.getX().getArray2DCopy())
                .withCompression(context.getResultSetting().getImageSetting().getCompressionValues()
                    .get(RED.getOrder()))
                .build())
            .getFourierData().getArray2DCopy()));
        vector.setY(new Array2D(transformation2D
            .process(FourierContext
                .start2DBuilder(vector.getY().getArray2DCopy())
                .withCompression(context.getResultSetting().getImageSetting().getCompressionValues()
                    .get(GREEN.getOrder()))
                .build())
            .getFourierData().getArray2DCopy()));
        vector.setZ(new Array2D(transformation2D
            .process(FourierContext
                .start2DBuilder(vector.getZ().getArray2DCopy())
                .withCompression(context.getResultSetting().getImageSetting().getCompressionValues()
                    .get(ImageSetting.RGB.BLUE.getOrder()))
                .build())
            .getFourierData().getArray2DCopy()));
      }
    }
  }

  public static class PostProcessingOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return context.getPixelList().size() > 0;
    }

    @Override
    public void apply(ImageContext context) {
      for (Vector3D<Array2D> vector : context.getPixelList()) {
        if (context.getResultSetting().getImageSetting().getImageSchema()
            .equals(ImageSetting.ImageSchema.YCRCB)) {
          converYCrCbToRGB(vector);
        }
        vector.setX(applyPostProcessing(vector.getX()));
        vector.setY(applyPostProcessing(vector.getY()));
        vector.setZ(applyPostProcessing(vector.getZ()));
      }
    }

    private Array2D applyPostProcessing(Array2D sourceArray) {
      double[][] array2D = normalizePixelArray(sourceArray.getArray2DCopy());
      return new Array2D(array2D);
    }
  }

  public static class CalculateMetricsOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return !context.getResultSetting().getTransformSetting().getMetricsTypes().isEmpty();
    }

    @Override
    public void apply(ImageContext context) {
      if (context.getResultSetting().getTransformSetting().getMetricsTypes().contains(MSE)) {
        Map<ImageSetting.RGB, Double> mseMap = new HashMap<>();
        mseMap.put(RED, getMSE(context, RED));
        mseMap.put(GREEN, getMSE(context, GREEN));
        mseMap.put(BLUE, getMSE(context, BLUE));
        context.getResultSetting().getMetrics().put(MSE, mseMap);
      }
      if (context.getResultSetting().getTransformSetting().getMetricsTypes().contains(PSNR)) {
        Map<ImageSetting.RGB, Double> psnrMap = new HashMap<>();
        psnrMap.put(RED, getPSNR(context, RED));
        psnrMap.put(GREEN, getPSNR(context, GREEN));
        psnrMap.put(BLUE, getPSNR(context, BLUE));
        context.getResultSetting().getMetrics().put(PSNR, psnrMap);
      }
    }

    private double getMSE(ImageContext context, ImageSetting.RGB colorChannel) {
      double result = 0.0;
      int width = context.getResultSetting().getResultImage().getWidth();
      int height = context.getResultSetting().getResultImage().getHeight();
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          Color sourcePixel = new Color(
              context.getResultSetting().getImageSetting().getSourceImage().getRGB(x, y));
          Color resultPixel = new Color(context.getResultSetting().getResultImage().getRGB(x, y));
          int sourceValue = 0;
          int resultValue = 0;
          switch (colorChannel) {
            case RED:
              sourceValue = sourcePixel.getRed();
              resultValue = resultPixel.getRed();
              break;
            case GREEN:
              sourceValue = sourcePixel.getGreen();
              resultValue = resultPixel.getGreen();
              break;
            case BLUE:
              sourceValue = sourcePixel.getBlue();
              resultValue = resultPixel.getBlue();
              break;
            default:
              break;
          }
          result += pow(abs(sourceValue - resultValue), 2);
        }
      }
      return result / (width * height);
    }

    private double getPSNR(ImageContext context, ImageSetting.RGB colorChannel) {
      double mse = context.getResultSetting().getMetrics().get(MSE) != null ?
          context.getResultSetting().getMetrics().get(MSE).get(colorChannel)
          : getMSE(context, colorChannel);
      return 10 * Math.log10(255 * 255 / mse);
    }
  }

  public static class StopCounterOperation implements Operation<ImageContext> {

    @Override
    public boolean validate(ImageContext context) throws IllegalArgumentException {
      return context.getStartTime() != null;
    }

    @Override
    public void apply(ImageContext context) {
      final long actualTime = System.nanoTime();
      context.getResultSetting().setTimeInMilles(actualTime - context.getStartTime());
    }
  }
}
