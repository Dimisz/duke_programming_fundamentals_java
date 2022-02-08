public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location location, double maxDistance){
        this.location = location;
        this.maxDistance = maxDistance;
    }

    public boolean satisfies(QuakeEntry qe) {
        double distance = location.distanceTo(qe.getLocation());
        return distance < maxDistance;
    }

    public String getName(){
        return getClass().getName();
    }
}
