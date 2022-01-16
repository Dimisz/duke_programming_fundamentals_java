import edu.duke.*;
import org.apache.commons.csv.*;



public class FirstCSVExample {

    public static void main(String[] args) {
        readFood();
    }

    public static void readFood(){
        FileResource fr = new FileResource("foods.csv");
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.print(record.get("Name") + "\t");
            System.out.print(record.get("Favorite Color") + "\t");
            System.out.println(record.get("Favorite Food"));
        }
    }
}
