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
            System.out.println(record.get("Name"));
        }
    }
}
