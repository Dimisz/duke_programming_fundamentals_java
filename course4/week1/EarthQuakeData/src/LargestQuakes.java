import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedata.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
//        for (QuakeEntry qe : list) {
//            System.out.println(qe);
//        }
//        int largestIndex = indexOfLargest(list);
//        System.out.println("Largest index: " + largestIndex);
//        System.out.println(list.get(largestIndex));
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 5);
        for(QuakeEntry qe : largestQuakes){
            System.out.println(qe);
        }
        System.out.println("Total found: " + largestQuakes.size());
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

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        // TO DO
        int numIterations = howMany < quakeData.size() ? howMany : quakeData.size();

        for(int j = 0; j < numIterations; j++) {
            int largest = indexOfLargest(copy);
            largestQuakes.add(copy.get(largest));
            copy.remove(largest);
        }
        return largestQuakes;
    }


    //ADDED FOR TESTING
    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
