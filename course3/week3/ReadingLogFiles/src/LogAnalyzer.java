import edu.duke.FileResource;

import java.util.ArrayList;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }

    public void readFile(){
        //WebLogParser logParser = new WebLogParser();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            //records.add(logParser.parseEntry(line));
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            String ipAddress = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> ipsOnDay = new ArrayList<String>();
        for(LogEntry le : records){
            String leDateAsString = le.getAccessTime().toString();
            String leDay = leDateAsString.substring(4, 10);
            if(leDay.equals(someday)){
                String ipAddress = le.getIpAddress();
                if(!ipsOnDay.contains(ipAddress)){
                    ipsOnDay.add(ipAddress);
                }
            }
        }
        return ipsOnDay;
    }


    /**
     *
     * @param num - status code
     * Prints out all the log entries with status code greater than num
     */
    public void printAllHigherThanNum(int num){
        for(LogEntry le : records){
            if(le.getStatusCode() > num){
                System.out.println(le);
            }
        }
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> ips = new ArrayList<String>();
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode >= low && statusCode <= high){
                String ip = le.getIpAddress();
                if(!ips.contains(ip)){
                    ips.add(ip);
                }
            }
        }
        return ips.size();
    }

    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }
}
