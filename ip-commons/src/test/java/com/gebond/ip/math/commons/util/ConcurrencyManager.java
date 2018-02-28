package com.gebond.ip.math.commons.util;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.transform.HaartTransformation2D;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static com.gebond.ip.math.commons.util.TestHelper.assertArrayEqualsWithDelta;

/**
 * Created by Gleb on 25.10.2017.
 */
@DisplayName("Multithreading")
public class ConcurrencyManager {

    public void consist_test_1() {
        HaartTransformation2D haartTransformation2D = new HaartTransformation2D();
        double[][] array1 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] result1 = haartTransformation2D.process(FourierContext.start2DBuilder(array1)
                .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
                .build())
                .getFourierData().getArray2DCopy();

        double[][] array2 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] result2 = haartTransformation2D.process(FourierContext.start2DBuilder(array2)
                .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
                .build())
                .getFourierData().getArray2DCopy();

        double[][] array3 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] result3 = haartTransformation2D.process(FourierContext.start2DBuilder(array3)
                .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
                .build())
                .getFourierData().getArray2DCopy();

        double[][] array4 = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        double[][] result4 = haartTransformation2D.process(FourierContext.start2DBuilder(array4)
                .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
                .build())
                .getFourierData().getArray2DCopy();
    }

    @Disabled
    @RepeatedTest(100)
    @DisplayName("threads = 2 iteration = 1")
    public void HaartFourierMultithreadingStressTest_2() throws InterruptedException {
        HaartTransformation2D transformation = new HaartTransformation2D();
        double[][] array = new double[][]{
                {-1.0, 21.0, 31.0, 40.0},
                {1.0, -2.0, 30.0, 4.0},
                {1.0, 20.0, -3.0, 4.0},
                {10.0, 21.0, 31.0, -4.0}
        };
        FourierContext.FourierContext2D fourierContext2D = buildFourierContext(array);

        MultithreadedStressTester stressTester = new MultithreadedStressTester(2,1000);
        stressTester.stress(() -> transformation.process(fourierContext2D));
        stressTester.shutdown();

        assertArrayEqualsWithDelta(array, fourierContext2D.getFourierData().getArray2DCopy());
    }

    private FourierContext.FourierContext2D buildFourierContext(double[][] array){
        return FourierContext
                .start2DBuilder(array)
                .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
                .build();
    }
}
