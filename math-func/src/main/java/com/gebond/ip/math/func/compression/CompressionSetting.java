package com.gebond.ip.math.func.compression;

/**
 * Created by Gleb on 17.10.2017.
 */
public class CompressionSetting {

    public static final double MAX_COMPRESSION_RATE = 100.0;
    public static final double MIN_COMPRESSION_RATE = 0.0;

    /**
     * Compression rate means remaining
     * Example:
     * compressionRate = 0 - nothing to compress
     * compressionRate = 50 - half of input array will be zero
     * compressionRate = 100 - whole input array will be zero
     */
    private final double compressionRate;

    public static CompressionSetting of(double compressionRate) {
        return new CompressionSetting(compressionRate);
    }

    private CompressionSetting(double compressionRate) {
        if (compressionRate < MIN_COMPRESSION_RATE || compressionRate > MAX_COMPRESSION_RATE) {
            throw new IllegalArgumentException("Compression rate is illegal. Found: " + compressionRate);
        }
        this.compressionRate = compressionRate;
    }

    public double getCompressionRate() {
        return compressionRate;
    }
}
