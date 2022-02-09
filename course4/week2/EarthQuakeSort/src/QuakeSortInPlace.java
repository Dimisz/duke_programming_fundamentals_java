
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    // ==========   SORTING BY DEPTH ==================
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int minIndex = from;
        for(int i = from + 1; i < quakes.size(); i++){
            if(quakes.get(i).getDepth() < quakes.get(minIndex).getDepth()){
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> quakes){
        for (int i=0; i < quakes.size(); i++) {
            int minIdx = getLargestDepth(quakes,i);
            QuakeEntry qi = quakes.get(i);
            QuakeEntry qmin = quakes.get(minIdx);
            quakes.set(i,qmin);
            quakes.set(minIdx,qi);
        }
    }
    // ==========   END OF SORTING BY DEPTH ===========

    // ==========   BUBBLE SORT  ======================
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //visualizing before a pass
//        System.out.println("\n\nBefore a pass:");
//        for(QuakeEntry qe : quakeData){
//            System.out.println(qe);
//        }


        for(int i = 0; i < quakeData.size() - numSorted - 1; i++){
            QuakeEntry currQuake = quakeData.get(i);
            QuakeEntry nextQuake = quakeData.get(i+1);
            if(currQuake.getMagnitude() > nextQuake.getMagnitude()){
                quakeData.set(i, nextQuake);
                quakeData.set(i+1, currQuake);
            }
        }

        //visualizing before a pass
//        System.out.println("\n\nAfter a pass:");
//        for(QuakeEntry qe : quakeData){
//            System.out.println(qe);
//        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size() - 1; i++){
            onePassBubbleSort(in, i);
        }
    }
    // ==========   END OF BUBBLE SORT  ===============
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/EarthquakeSortStarterProgram/data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "/Users/mbpro/Downloads/EarthquakeSortStarterProgram/data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}


    public static void main(String[] args) {
        QuakeSortInPlace quakeSortInPlace = new QuakeSortInPlace();
        quakeSortInPlace.testSort();
    }
}
