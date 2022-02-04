import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = 0;
        for(String key : counts.keySet()){
            int currentCount = counts.get(key);
            if(currentCount > max) max = currentCount;
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> IPsWithMostVisit = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for(String ipAddress : counts.keySet()){
            if(counts.get(ipAddress) == maxVisits){
                IPsWithMostVisit.add(ipAddress);
            }
        }
        return IPsWithMostVisit;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipsByDays = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            String leDateAsString = le.getAccessTime().toString();
            String leDay = leDateAsString.substring(4, 10);
            if(!ipsByDays.containsKey(leDay)){
                ArrayList<String> ips = new ArrayList<String>();
                String ip = le.getIpAddress();
                ips.add(ip);
                ipsByDays.put(leDay, ips);
            }
            else{
                ArrayList<String> ips = new ArrayList<String>();
                ips = ipsByDays.get(leDay);
                String ip = le.getIpAddress();
                ips.add(ip);
                ipsByDays.put(leDay, ips);
            }
        }
        return ipsByDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsByDay){
        String dayWithMaxVisits = "No records";
        int maxVisitsPerDay = 0;
        for(String day : ipsByDay.keySet()){
            int visitsPerDay = ipsByDay.get(day).size();
            if(visitsPerDay > maxVisitsPerDay){
                maxVisitsPerDay = visitsPerDay;
                dayWithMaxVisits = day;
            }
        }
        return dayWithMaxVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String day){
        ArrayList<String> ips = new ArrayList<String>();
        HashMap<String, Integer> ipsCounts = new HashMap<String, Integer>();
        ips = counts.get(day);
        for(String ip : ips){
            if(!ipsCounts.containsKey(ip)){
                ipsCounts.put(ip, 1);
            }
            else {
                ipsCounts.put(ip, ipsCounts.get(ip) + 1);
            }
        }
        ArrayList<String> ipsMostVisits = iPsMostVisits(ipsCounts);
        return ipsMostVisits;
    }
}
