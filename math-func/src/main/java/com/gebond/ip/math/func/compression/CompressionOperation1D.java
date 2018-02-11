package com.gebond.ip.math.func.compression;

import com.gebond.ip.math.func.operation.CompressingOperation;
import com.gebond.ip.math.func.operation.FourierContext;

import static com.gebond.ip.math.func.compression.CompressionSetting.MIN_COMPRESSION_RATE;

/**
 * Created on 10/02/18.
 */
public class CompressionOperation1D extends CompressingOperation<FourierContext.FourierContext1D> {
    @Override
    public void apply(FourierContext.FourierContext1D context) {
        if(context.getCompressionSetting().getCompressionRate() == MIN_COMPRESSION_RATE){
            return; // no compression is required
        }

    }
}
