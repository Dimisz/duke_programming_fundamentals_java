import java.util.ArrayList;
import java.util.Date;

public class Tester {
    public static void testLogEntry(){
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request",
                                    200, 500);
        System.out.println(le);

        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2",
                200, 500);
        System.out.println(le2);
    }

    public static void testLogAnalyzer(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile();
        logAnalyzer.printAll();
    }

    public static void testUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(); // choose file from your resource
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public static void testPrintAllHigherThanNum(int num){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAllHigherThanNum(num);
    }

    public static void testUniqueIPVisitsOnDay(String day){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        ArrayList<String> ips = la.uniqueIPVisitsOnDay(day);
        System.out.println("There are " + ips.size() + " unique ips");
        for(String ip : ips){
            System.out.println(ip);
        }
    }

    public static void testCountUniqueIPsInRange(int low, int high){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        int unique = la.countUniqueIPsInRange(low, high);
        System.out.println(unique);
    }

    public static void main(String[] args) {
//        testLogEntry();
//        testLogAnalyzer();
//        testUniqueIPs();
//        testPrintAllHigherThanNum(400);
//        testUniqueIPVisitsOnDay("Sep 27");
//        testCountUniqueIPsInRange(400, 499);
    }
}
