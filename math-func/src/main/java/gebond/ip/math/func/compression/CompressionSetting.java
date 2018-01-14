package gebond.ip.math.func.compression;

/**
 * Created by Gleb on 17.10.2017.
 */
public abstract class CompressionSetting {

    protected double compressionRate;

    abstract Object getTarget();

    CompressionSetting(double compressionRate) {
        this.compressionRate = compressionRate;
    }

    public double getCompressionRate() {
        return compressionRate;
    }

    public void setCompressionRate(double compressionRate) {
        this.compressionRate = compressionRate;
    }
}
