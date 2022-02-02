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

    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }
}
