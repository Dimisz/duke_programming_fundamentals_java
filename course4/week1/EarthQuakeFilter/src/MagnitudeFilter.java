public class MagnitudeFilter implements Filter{
    private double minMagnitude;
    private double maxMagnitude;

    public MagnitudeFilter(double minMagnitude, double maxMagnitude){
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
    }

    public boolean satisfies(QuakeEntry qe){
        double magnitude = qe.getMagnitude();
        return (magnitude >= minMagnitude) && (magnitude <= maxMagnitude);
    }
}
