package com.gebond.ip.math.func.compression;

/**
 * Created by Gleb on 17.10.2017.
 */
@Deprecated
public abstract class Compression<T extends CompressionSetting> {

    private static double MAX_COMPRESSION_RATE = 100.0;
    private static double MIN_COMPRESSION_RATE = 0.0;

    T compress(T compressionSetting) {
        if (compressionSetting == null) {
            throw new IllegalArgumentException("Compression target is null");
        }
        if (compressionSetting.getCompressionRate() < MIN_COMPRESSION_RATE
                || compressionSetting.getCompressionRate() > MAX_COMPRESSION_RATE) {
            throw new IllegalArgumentException("Compression rate " + compressionSetting.getCompressionRate() + " is incorrect.");
        }
        if (compressionSetting.getCompressionRate() == MIN_COMPRESSION_RATE) {
            return compressionSetting; // no compression is required
        }
        return doCompress(compressionSetting);
    }

    abstract protected T doCompress(T compressionSetting);
}
