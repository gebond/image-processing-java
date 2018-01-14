package gebond.ip.math.func.compression;

/**
 * Created by Gleb on 17.10.2017.
 */
public abstract class Compression<T extends CompressionSetting> {

    private static double MAX_COMPRESSION_RATE = 100.0;
    private static double MIN_COMPRESSION_RATE = 0.0;

    T compress(T compressionSetting) {
        if (compressionSetting.getTarget() == null) {
            throw new IllegalArgumentException("Compression target is null");
        }
        if (compressionSetting.compressionRate < MIN_COMPRESSION_RATE
                || compressionSetting.compressionRate > MAX_COMPRESSION_RATE) {
            throw new IllegalArgumentException("Compression rate " + compressionSetting.compressionRate + " is incorrect.");
        }
        if (compressionSetting.compressionRate == MIN_COMPRESSION_RATE) {
            return compressionSetting; // no compression is required
        }
        return doCompress(compressionSetting);
    }

    abstract protected T doCompress(T compressionSetting);
}
