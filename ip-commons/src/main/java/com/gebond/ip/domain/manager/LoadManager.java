package gebond.ip.domain.manager;

import com.gebond.ip.math.func.transform.FourierData;
import com.gebond.ip.math.func.transform.HaartFourierTransformation1D;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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

    static HaartFourierTransformation1D transformation1D = new HaartFourierTransformation1D();

    public static void main(String[] args) {
        double[] times = new double[TEST_RUNS];
        long startTime;
        long endTime;

        for (int i = 0; i < TEST_RUNS; i++) {
            FourierData fourierData = newFourierData();
            startTime = System.currentTimeMillis();
            transformation1D.analysis(fourierData);
            transformation1D.synthesis(fourierData);
            endTime = System.currentTimeMillis();
            times[i] = (endTime - startTime);
        }

//        times = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        times = new double[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        // ANALYSIS
        Arrays.sort(times);
        double min = times[0];
        double max = times[times.length - 1];

        double step = (max - min) / PERIODS;

        double pcnt50_val = 0.0;
        double pcnt85_val = 0.0;
        double pcnt95_val = 0.0;

        for (int i = 0; i < TEST_RUNS; i++) {
            if (i <= (int) (TEST_RUNS * BOUND_50 / PERIODS)) {
                continue;
            } else if (i <= (int) (TEST_RUNS * BOUND_85 / PERIODS)) {
                pcnt50_val = i * step;
            } else if (i <= (int) (TEST_RUNS * BOUND_95 / PERIODS)) {
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

    private static FourierData newFourierData() {
        double[][] result = new double[ARRAY_SIZE][ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            for (int j = 0; j < ARRAY_SIZE; j++) {
                result[i][j] = ThreadLocalRandom.current().nextDouble(MIN_DOUBLE, MAX_DOUBLE);
            }
        }
        return new FourierData(result);
    }
}
