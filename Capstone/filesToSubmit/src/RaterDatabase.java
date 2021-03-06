import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;

    private static void initialize() {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,Rater>();

            addRatings(filename);
            System.out.println("Added ratings");
        }
    }

    public static void addRatings(String filename) {
        try {
            initialize();
            Reader in = new FileReader(filename);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
            FileResource fr = new FileResource(filename);
            CSVParser csvp = fr.getCSVParser();
            for (CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id, item, Double.parseDouble(rating));
            }
        }
        catch(IOException ie){
            System.out.println("Unable to read the file within addRatings");
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        //initialize();
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static Rater getRater(String id) {
        //initialize();

        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        //initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());

        return list;
    }

    public static int size() {
        return ourRaters.size();
    }



}
