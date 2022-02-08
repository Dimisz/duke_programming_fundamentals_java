import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                   String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start")){
                if(title.startsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if(where.equals("end")){
                if(title.endsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if(where.equals("any")){
                if(title.contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listBig = filterByPhrase(list, "any", "Creek");
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
        System.out.println("Found " + listBig.size() + " quakes to match that criteria");
    }


    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                                   double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double depth = qe.getDepth();
            if(depth > minDepth && depth < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listBig = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
        System.out.println("Found " + listBig.size() + " quakes to match that criteria");
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
//            System.out.println(loc.distanceTo(from));
            if(loc.distanceTo(from)/1000 < (distMax)){ // Location class calculates distance in meters
                answer.add(qe);
               // System.out.println("added");
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

//        for(QuakeEntry qe : list){
//            if(qe.getMagnitude() > 5.0){
//                System.out.println(qe);
//            }
//        }
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
        System.out.println("Found " + listBig.size() + " quakes to match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000, city);
        for(QuakeEntry qe : answer){
            Location loc = qe.getLocation();
            double distance = loc.distanceTo(city) / 1000;
            System.out.println(distance + " " + qe.getInfo());
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "/Users/mbpro/Downloads/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }


    }


    public static void main(String[] args) {
        EarthQuakeClient eqClient = new EarthQuakeClient();
//        eqClient.createCSV();
//        eqClient.bigQuakes();
//        eqClient.closeToMe();
        eqClient.quakesOfDepth();
//        eqClient.quakesByPhrase();
    }
    
}
