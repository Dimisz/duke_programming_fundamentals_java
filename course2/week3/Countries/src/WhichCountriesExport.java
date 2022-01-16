import edu.duke.*;
import org.apache.commons.csv.*;


public class WhichCountriesExport {
    public static void main(String[] args) {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
//        countryInfo(parser, "Nauru");
//        findExport();
//        listExportersTwoProducts(parser, "gold", "diamonds");
//        System.out.println(numberOfExporters(parser, "gold"));
        bigExporters(parser, "$999,999,999,999");
    }

    public static void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                System.out.print(record.get("Country") + "\t");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem){
        int exportersCount = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                exportersCount++;
            }
        }
        return exportersCount;
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        boolean found = false;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
                found = true;
            }
        }
        if(!found){
            System.out.println("NOT FOUND");
        }
    }
    public static void countryInfo(CSVParser parser, String country){
        boolean found = false;
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                System.out.print(record.get("Country") + ":\t");
                System.out.print(record.get("Exports") + "\t");
                System.out.println(record.get("Value (dollars)"));
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("NOT FOUND");
        }
    }

    public static void findExport(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String exportOfInterest = "coffee";
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportOfInterest)){
                System.out.print(record.get("Country") + "\t");
                System.out.println(record.get("Exports"));
            }

        }
    }
}
