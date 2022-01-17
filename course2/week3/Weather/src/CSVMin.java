import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;


public class CSVMin {
    public static void main(String[] args) {
        //testColdestInDay();
        //testColdestInManyDays();
        //System.out.println(fileWithColdestTemperature());
        testFileWithColdestTemperature();
    }
    public static void printFile(String filepath){
        FileResource fr = new FileResource(filepath);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord record : parser){
            smallestSoFar = getSmallestOfTwo(record, smallestSoFar);
        }
        return smallestSoFar;

    }

    public static void testColdestInDay(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                " at " + smallest.get("TimeEST"));
    }
// Quiz ex 2
    public static String fileWithColdestTemperature(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = null;
        for(File f : dr.selectedFiles()){
            if(filename == null){
                //filename = f.getName();
                filename = f.getAbsolutePath();
            }

            FileResource fr = new FileResource(f);

            CSVRecord current = coldestHourInFile(fr.getCSVParser());

            if(smallestSoFar == null){
                smallestSoFar = current;
            }

            if (Double.parseDouble(current.get("TemperatureF")) < Double.parseDouble(smallestSoFar.get("TemperatureF")) && Double.parseDouble(current.get("TemperatureF")) != -9999) {
                //filename = f.getName();
                filename = f.getAbsolutePath();
            }
            smallestSoFar = getSmallestOfTwo(current, smallestSoFar);

        }
        return filename;
    }
//Q2 part 2
    public static void testFileWithColdestTemperature(){
        String coldestDayPath = fileWithColdestTemperature();
        int separatorIndex = coldestDayPath.lastIndexOf("/") + 1;
        String coldestDayFilename = coldestDayPath.substring(separatorIndex);
        System.out.println("Coldest day was in file " + coldestDayFilename);

        FileResource fr = new FileResource(coldestDayPath);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        printFile(coldestDayPath);
    }

    public static CSVRecord coldestInManyDays(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);

            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(current, smallestSoFar);
        }
        return smallestSoFar;
    }

    public static CSVRecord getSmallestOfTwo(CSVRecord current, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = current;
        }
        else{
            double smallestTemperature = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            double currTemperature = Double.parseDouble(current.get("TemperatureF"));
            if(currTemperature < smallestTemperature && currTemperature != -9999){
                //smallestTemperature = currTemperature;
                smallestSoFar = current;
            }
        }
        return smallestSoFar;
    }


    public static void testColdestInManyDays(){
        CSVRecord smallest = coldestInManyDays();
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                " at " + smallest.get("DateUTC"));
    }

}
