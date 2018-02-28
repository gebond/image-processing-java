package gebond.ip.domain.manager;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.transform.HaartTransformation2D;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;

/**
 * Created by Gleb on 22.10.2017.
 */
public class LoadManager {

    static int TEST_RUNS = 10;

    static int ARRAY_SIZE = 16;

    static double MAX_DOUBLE = 1;
    static double MIN_DOUBLE = 0;

    static double PERIODS = 100;
    static double BOUND_50 = 50;
    static double BOUND_85 = 85;
    static double BOUND_95 = 95;

    static HaartTransformation2D haartTransformation2D= new HaartTransformation2D();

    public static void main(String[] args) {
        double[] counts = new double[TEST_RUNS];

        // FILL BENCHMARK INFO
        for (int i = 0; i < TEST_RUNS; i++) {
            long startTime = System.currentTimeMillis();
            haartTransformation2D.process(newFourierContext2D());
            long endTime = System.currentTimeMillis();
            counts[i] = (endTime - startTime);
        }

        // ANALYSIS
        Arrays.sort(counts);
        double min = counts[0];
        double max = counts[counts.length - 1];

        double step = (max - min) / 100.0;

        double pcnt50_val = 0.0;
        double pcnt85_val = 0.0;
        double pcnt95_val = 0.0;

        for (int i = 0; i < TEST_RUNS; i++) {
            if (i <= (int) (TEST_RUNS * BOUND_50 / 100)) {
                continue;
            } else if (i <= (int) (TEST_RUNS * BOUND_85 / 100)) {
                pcnt50_val = i * step;
            } else if (i <= (int) (TEST_RUNS * BOUND_95 / 100)) {
                pcnt85_val = i * step;
            } else {
                pcnt95_val = i * step;
            }
        }
        System.out.println("Max= " + max + " ms \nMin= " + min + " ms");
        System.out.println(pcnt50_val + " ms - " + "50%\n"
                + pcnt85_val + " ms - " + "85%\n"
                + pcnt95_val + " ms - " + "95%");

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
