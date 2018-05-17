package gebond.ip.domain.manager;

import static com.gebond.ip.math.func.image.ImageTestHelper.getImageUsingFileName;
import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.image.ImageProcessor;
import com.gebond.ip.math.func.image.ImageProcessorImpl;
import com.gebond.ip.math.func.transform.HaartTransformation2D;
import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ImageSetting.ImageSchema;
import com.gebond.ip.model.setting.ImageSetting.SegmentSize;
import com.gebond.ip.model.setting.TransformSetting;
import com.gebond.ip.model.setting.TransformSetting.TransformationType;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Gleb on 22.10.2017.
 */
public class LoadManager {

  static int TEST_RUNS = 100;
  static int ARRAY_SIZE = 16;
  static double MAX_DOUBLE = 1;
  static double MIN_DOUBLE = 0;
  static double BOUND_50 = 50;
  static double BOUND_85 = 85;
  static double BOUND_95 = 95;

  static HaartTransformation2D haartTransformation2D = new HaartTransformation2D();
  static ImageProcessor imageProcessor = new ImageProcessorImpl();

  public static void main(String[] args) throws IOException {
    double[] counts = new double[TEST_RUNS];

    // FILL BENCHMARK INFO
    for (int i = 0; i < TEST_RUNS; i++) {
      long startTime = System.currentTimeMillis();
//      runFourier2D();
      runImageProcess();
      long endTime = System.currentTimeMillis();
      counts[i] = (endTime - startTime);
    }

    // ANALYSIS
    Arrays.sort(counts);
    double min = counts[0];
    double max = counts[counts.length - 1];

    double pcnt50_val = counts[(int) (TEST_RUNS * BOUND_50 / 100)];
    double pcnt85_val = counts[(int) (TEST_RUNS * BOUND_85 / 100)];
    double pcnt95_val = counts[(int) (TEST_RUNS * BOUND_95 / 100)];

    System.out.println("Max= " + max + " ms \nMin= " + min + " ms");
    System.out.println(pcnt50_val + " ms - " + "50%\n"
        + pcnt85_val + " ms - " + "85%\n"
        + pcnt95_val + " ms - " + "95%");
  }

  private static void runFourier2D() {
    haartTransformation2D.process(newFourierContext2D());
  }

  private static void runImageProcess() throws IOException {
    ImageSetting imageSetting = ImageSetting.startBuilder()
        .withImage(getImageUsingFileName("64x64.png"))
        .withSchema(ImageSchema.RGB)
        .withSegmentSize(SegmentSize.X32)
        .withCompressions(new HashMap<Integer, CompressionSetting>() {
          {
            put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
            put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
            put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
          }
        })
        .build();
    TransformSetting transformSetting = TransformSetting.startBuilder()
        .withType(TransformationType.HAART_TRANSFORM)
        .build();
    imageProcessor.processImage(imageSetting, transformSetting);
  }

  private static FourierContext.FourierContext2D newFourierContext2D() {
    double[][] result = new double[ARRAY_SIZE][ARRAY_SIZE];
    for (int i = 0; i < ARRAY_SIZE; i++) {
      for (int j = 0; j < ARRAY_SIZE; j++) {
        result[i][j] = ThreadLocalRandom.current().nextDouble(MIN_DOUBLE, MAX_DOUBLE);
      }
    }
    return FourierContext.start2DBuilder(result)
        .withCompression(CompressionSetting.of(MIN_COMPRESSION_RATE))
        .build();
  }
}
