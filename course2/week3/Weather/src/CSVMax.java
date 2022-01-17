import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class CSVMax {

    public static void main(String[] args) {
        testHottestInDay();
        //testHottestInManyDays();
    }


    public static CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord record : parser){
            largestSoFar = getLargestOfTwo(record, largestSoFar);
        }
        return largestSoFar;
    }

    public static void testHottestInDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }

    public static CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);

            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(current, largestSoFar);
        }
        return largestSoFar;
    }

    public static CSVRecord getLargestOfTwo(CSVRecord current, CSVRecord largestSoFar){
        if(largestSoFar == null){
            largestSoFar = current;
        }
        else{
            double largestTemperature = Double.parseDouble(largestSoFar.get("TemperatureF"));
            double currTemperature = Double.parseDouble(current.get("TemperatureF"));
            if(currTemperature > largestTemperature){
                //largestTemperature = currTemperature;
                largestSoFar = current;
            }
        }
        return largestSoFar;
    }


    public static void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("DateUTC"));
    }
}
