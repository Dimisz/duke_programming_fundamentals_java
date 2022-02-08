import java.util.*;
import edu.duke.*;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) {
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/EarthquakeFilterStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
//        Filter f1 = new MagnitudeFilter(4.0, 5.0);
//        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        Filter f1 = new DistanceFilter(new Location(35.42, 139.43), 10_000_000);
      //  Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7  = filter(list, f1);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public void testMatchAllFilters(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/EarthquakeFilterStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
//        for(QuakeEntry qe : list){
//            System.out.println(qe);
//        }
        MatchAllFilters maf = new MatchAllFilters();
        Filter magnitudeFilter = new MagnitudeFilter(0.0, 2.0);
        maf.addFilter(magnitudeFilter);
        Filter depthFilter = new DepthFilter(-100_000.0, -10_000);
        maf.addFilter(depthFilter);
        Filter phraseFilter = new PhraseFilter("any", "a");
        maf.addFilter(phraseFilter);

        ArrayList<QuakeEntry> filteredQuakes = filter(list, maf);
        for(QuakeEntry qe : filteredQuakes){
            System.out.println(qe);
        }
    }

    public void testMatchAllFilters2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
//        for(QuakeEntry qe : list){
//            System.out.println(qe);
//        }
        MatchAllFilters maf = new MatchAllFilters();


        Filter locFilter = new DistanceFilter(new Location(55.7308, 9.1153), 3_000_000.0);
        maf.addFilter(locFilter);
        Filter magFilter = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(magFilter);
        Filter phraseFilter = new PhraseFilter("any", "e");
        maf.addFilter(phraseFilter);



        ArrayList<QuakeEntry> filteredQuakes = filter(list, maf);
        System.out.println("Results found: " + filteredQuakes.size());
        for(QuakeEntry qe : filteredQuakes){
            System.out.println(qe);
        }

        System.out.println("Filters used: " + maf.getName());
    }


    //testing
    public static void main(String[] args) {
        EarthQuakeClient2 eq = new EarthQuakeClient2();
 //       eq.quakesWithFilter();
//        eq.testMatchAllFilters();
        eq.testMatchAllFilters2();
    }
}
