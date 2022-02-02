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

    public static void main(String[] args) {
//        testLogEntry();
//        testLogAnalyzer();
        testUniqueIPs();
    }
}
