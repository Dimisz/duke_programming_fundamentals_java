import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {

    public static void main(String[] args) {
        //printNames();
//        testTotalBirths();
//        System.out.println(getRank("Frank", "M"));
//        System.out.println(getName(450, "M"));
//        whatIsNameInYear("Owen", 1974, 2014, "M");
//        System.out.println(yearOfHighestRank("Mich", "M"));
//        System.out.println(getAverageRank("Vladimir", "M"));
        System.out.println(getTotalBirthsRankedHigher("Drew", "M"));
    }

    public static int getRank(String name, String gender, File f){
        int rank = 0;
        FileResource fr = new FileResource(f);
        for(CSVRecord rec : fr.getCSVParser(false)){ //false means the file doesn't have a header row
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    //overloaded without passing file address - will be chosen manually
    public static int getRank(String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){ //false means the file doesn't have a header row
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }

    //Q3
    public static String getName(int rank, String gender){
        int rankCounter = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){ //false means the file doesn't have a header row
            if(rec.get(1).equals(gender)){
                rankCounter++;
                if(rankCounter == rank){
                    return (rec.get(0));
                }
            }
        }
        return "NO NAME";
    }
// Q4
    public static void whatIsNameInYear(String name, int yearBorn, int targetYear, String gender){
        String pronoun = gender.equals("F") ? "she" : "he";
        int rank = getRank(name, gender);
        String resultName = getName(rank, gender);
        System.out.println(name + " born in " + yearBorn +
                " would be " + resultName + " if " + pronoun +
                " was born in " + targetYear + ".");
    }

    // Q5 yearOfHighestRank
    public static String yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int currentRank = -1;
        String year = "none";
        for(File f : dr.selectedFiles()){
            int newRank = getRank(name, gender, f);
            if(newRank != -1 && currentRank == -1){
                currentRank = newRank;
                year = f.getName();
//                int ind = fname.indexOf("2");
//                String yearAsStr = fname.substring(ind, ind+3);
//                year = Integer.parseInt(yearAsStr);
            }
            else if(newRank < currentRank){
                currentRank = newRank;
                year = f.getName();
//                int ind = fname.indexOf("2");
//                String yearAsStr = fname.substring(ind, ind+4);
//                year = Integer.parseInt(yearAsStr);
               // System.out.println("Rank " + currentRank + " year " + year);
            }
        }
        return year;
    }

    //Q6 getAverageRank
    public static double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double ranks = 0;
        int count = 0;
        for(File f : dr.selectedFiles()){
            count++;
            int newRank = getRank(name, gender, f);
            if(newRank != -1){
                ranks += newRank;
            }
        }
        if(ranks == 0 || count == 0){
            return -1.0;
        }
        else {
            return ranks / count;
        }
    }

    public static int getTotalBirthsRankedHigher(String name, String gender){
        int totalBirths = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                break;
            }
            if(rec.get(1).equals(gender)){
                int num = Integer.parseInt(rec.get(2));
                totalBirths += num;
            }
        }
        return totalBirths;
    }

    public static void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){ //false means the file doesn't have a header row
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100 ) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public static void totalBirths(FileResource fr){
        int total = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numNamesTotal = 0;
        int numNamesGirls = 0;
        int numNamesBoys = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int currentNum = Integer.parseInt(rec.get(2));
            total += currentNum;
            numNamesTotal++;
            if(rec.get(1).equals("F")){
                totalGirls += currentNum;
                numNamesGirls++;
            }
            else{
                totalBoys += currentNum;
                numNamesBoys++;
            }
        }
        System.out.println("Total births: " + total);
        System.out.println("Total names: " + numNamesTotal);
        System.out.println("Total girls born: " + totalGirls);
        System.out.println("Total girls' names: " + numNamesGirls);
        System.out.println("Total boys born: " + totalBoys);
        System.out.println("Total boys' names: " + numNamesBoys);
    }

    public static void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
