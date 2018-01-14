package com.gebond.ip.math.common.util;

import com.gebond.ip.math.func.transform.FourierData;
import com.gebond.ip.math.func.transform.FourierTransformation;
import com.gebond.ip.math.func.transform.HaartFourierTransformation1D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gebond.ip.math.common.util.ArrayUtil.arrayCopy;
import static org.apache.commons.math3.util.FastMath.pow;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Created by Gleb on 25.10.2017.
 */
@DisplayName("Multithreading")
public class ConcurrencyManager {

    public void consist_test_1() {
        FourierTransformation transformation = new HaartFourierTransformation1D();
        double[][] array1 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        FourierData fourierData1 = new FourierData(array1);
        transformation.analysis(fourierData1);
        transformation.synthesis(fourierData1);
        double[][] result1 = fourierData1.getArray2D();

        double[][] array2 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        FourierData fourierData2 = new FourierData(array2);
        transformation.analysis(fourierData2);
        transformation.synthesis(fourierData2);
        double[][] result2 = fourierData2.getArray2D();

        double[][] array3 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        FourierData fourierData3 = new FourierData(array3);
        transformation.analysis(fourierData3);
        transformation.synthesis(fourierData3);
        double[][] result3 = fourierData3.getArray2D();

        double[][] array4 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        FourierData fourierData4 = new FourierData(array4);
        transformation.analysis(fourierData4);
        transformation.synthesis(fourierData4);
        double[][] result4 = fourierData4.getArray2D();
    }

    @Test
    @DisplayName("threads = 2 iteration = 10000")
    public void HaartFourierMultithreadingStressTest_2() throws InterruptedException {
        FourierTransformation transformation = new HaartFourierTransformation1D();
        double[][] array1 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] array1_copy = new double[array1.length][array1.length];
        ArrayUtil.arrayCopy(array1, array1_copy);
        FourierData fourierData = new FourierData(array1);

        MultithreadedStressTester stressTester = new MultithreadedStressTester(2,1);
        stressTester.stress(() -> {
            transformation.analysis(fourierData);
            transformation.synthesis(fourierData);
        });
        stressTester.shutdown();

        for (int i = 0; i < array1.length; i++) {
            assertArrayEquals(array1_copy[i], fourierData.getArray2D()[i], pow(10, -10));
        }
    }

    @Test
    @DisplayName("threads = 4 iteration = 10000")
    public void HaartFourierMultithreadingStressTest_4() throws InterruptedException {
        FourierTransformation transformation = new HaartFourierTransformation1D();
        double[][] array1 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] array1_copy = new double[array1.length][array1.length];
        ArrayUtil.arrayCopy(array1, array1_copy);
        FourierData fourierData = new FourierData(array1);

        MultithreadedStressTester stressTester = new MultithreadedStressTester(4,10000);
        stressTester.stress(() -> {
            transformation.synthesis(fourierData);
            transformation.analysis(fourierData);
        });
        stressTester.shutdown();

        for (int i = 0; i < array1.length; i++) {
            assertArrayEquals(array1_copy[i], fourierData.getArray2D()[i], pow(10, -10));
        }
    }

    @Test
    @DisplayName("threads = 8 iteration = 10000")
    public void HaartFourierMultithreadingStressTest_8() throws InterruptedException {
        FourierTransformation transformation = new HaartFourierTransformation1D();
        double[][] array1 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] array1_copy = new double[array1.length][array1.length];
        ArrayUtil.arrayCopy(array1, array1_copy);
        FourierData fourierData = new FourierData(array1);

        MultithreadedStressTester stressTester = new MultithreadedStressTester(8,10000);
        stressTester.stress(() -> {
            transformation.analysis(fourierData);
            transformation.synthesis(fourierData);
        });
        stressTester.shutdown();

        for (int i = 0; i < array1.length; i++) {
            assertArrayEquals(array1_copy[i], fourierData.getArray2D()[i], pow(10, -10));
        }
    }
}
