public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;

    public DepthFilter(double minDepth, double maxDepth){
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        return (depth >= minDepth) && (depth <= maxDepth);
    }

    public String getName(){
        return getClass().getName();
    }
}
