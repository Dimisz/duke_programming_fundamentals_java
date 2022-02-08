import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
//        for (QuakeEntry qe : list) {
//            System.out.println(qe);
//        }
        int largestIndex = indexOfLargest(list);
        System.out.println("Largest index: " + largestIndex);
        System.out.println(list.get(largestIndex));
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int largestIndex = 0;
        double largestMagnitude = 0;
        for(int i = 0; i < data.size(); i++){
            double currentMagnitude = data.get(i).getMagnitude();
            if(currentMagnitude > largestMagnitude){
                largestMagnitude = currentMagnitude;
                largestIndex = i;
            }
        }
        return largestIndex;
    }


    //ADDED FOR TESTING
    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
