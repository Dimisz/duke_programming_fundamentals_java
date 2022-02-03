import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class CountTester {

    public static void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        for(String key : counts.keySet()){
            System.out.println(key + ":\t\t" + counts.get(key));
        }
    }

    public static void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println(maxVisits);
    }

    public static void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ipsMostVisits = la.iPsMostVisits(counts);
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most visits: " + maxVisits);
        for(String ip : ipsMostVisits){
            System.out.println(ip);
        }
    }

    public static void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> ipsByDays = la.iPsForDays();
        for(String key : ipsByDays.keySet()){
            System.out.println(key + ":");
            for(String ip : ipsByDays.get(key)){
                System.out.println("\t" + ip);
            }
        }
    }

    public static void main(String[] args) {
//        testCounts();
//        testMostNumberVisitsByIP();
//        testIPsMostVisits();
        testIPsForDays();
    }
}
