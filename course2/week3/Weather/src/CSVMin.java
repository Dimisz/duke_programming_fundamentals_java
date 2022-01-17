import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;


public class CSVMin {
    public static void main(String[] args) {
//        testColdestInDay();
        testColdestInManyDays();
        //System.out.println(fileWithColdestTemperature());
        //testFileWithColdestTemperature();
//        testLowestHumidityInFile();
//        testLowestHumidityInManyFiles();
//        testAverageTemperatureInFile();
//        testAverageTemperatureWithHighHumidityInFile(80);
    }

    //Q5 averageTemperatureInFile
    public static double averageTemperatureInFile(CSVParser parser){
        double sumOfTemperatures = 0;
        int count = 0;
        for(CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if(temp != -9999){
                sumOfTemperatures += temp;
                count++;
            }
        }
        return sumOfTemperatures / count;
    }

    public static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }

    // Q6 averageTemperatureWithHighHumidityInFile
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double humidity){
        double sumOfTemperatures = 0;
        int count = 0;
        for(CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            double currentHumidity = record.get("Humidity").equals("N/A") ? -1 : Double.parseDouble(record.get("Humidity"));
            if(temp != -9999 && currentHumidity >= humidity){
                sumOfTemperatures += temp;
                count++;
            }
        }

        if(count == 0){
            return -1;
        }
        return sumOfTemperatures / count;
    }

    public static void testAverageTemperatureWithHighHumidityInFile(double humidity){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, humidity);
        if(avg == -1){
            System.out.println("No temperature with that humidity");
        }
        else{
            System.out.println("Average temperature when high humidity is " + avg);
        }
    }
    //Q3 lowest humidity in a file
    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord record : parser){
            smallestSoFar = getSmallestHumidityOfTwo(record, smallestSoFar);
        }
        return smallestSoFar;

    }

    public static CSVRecord getSmallestHumidityOfTwo(CSVRecord current, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = current;
        }
        else{
            if(!current.get("Humidity").equals("N/A")) {
                double smallestTemperature = Double.parseDouble(smallestSoFar.get("Humidity"));
                double currTemperature = Double.parseDouble(current.get("Humidity"));
                if (currTemperature < smallestTemperature && currTemperature != -9999) {
                    //smallestTemperature = currTemperature;
                    smallestSoFar = current;
                }
            }
        }
        return smallestSoFar;
    }

    public static void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
                " at " + smallest.get("DateUTC"));
    }


    // Q4 lowest humidity in many files
    public static CSVRecord lowestHumidityInManyFiles(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);

            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestHumidityOfTwo(current, smallestSoFar);
        }
        return smallestSoFar;
    }

    public static void testLowestHumidityInManyFiles(){
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
                " at " + smallest.get("DateUTC"));
    }


    ///////



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
                " at " + smallest.get("DateUTC"));
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
