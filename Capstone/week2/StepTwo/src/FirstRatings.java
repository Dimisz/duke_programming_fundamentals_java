import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    private ArrayList<Movie> moviesInfo;
    private ArrayList<Rater> raters;
    private HashMap<String, Integer> numberOfRatingsByRater;

    public FirstRatings(String moviesFilename, String ratersFilename){
        try {
            moviesInfo = loadMovies(moviesFilename);
        }
        catch(IOException ioe){
            System.out.println("Failed to load movies...");
        }

        try {
            raters = loadRaters(ratersFilename);
        }
        catch(IOException ioe){
            System.out.println("Failed to load raters...");
        }
    }

    public ArrayList<Movie> getMovies(){
        return moviesInfo;
    }

    public ArrayList<Rater> getRaters(){
        return raters;
    }

    public ArrayList<Movie> getMoviesInfo(){
        return moviesInfo;
    }
    //====================================================================
    //==================== WORKING WITH MOVIES ===========================
    /*
    @param filename - path to a csv file to read from
    - Reads the CSV file,
    - using that data creates objects of class Movie
    - appends the objects to the ArrayList<Movie> moviesInfo
     */
    public ArrayList<Movie> loadMovies(String filename) throws IOException {
        Reader in = new FileReader(filename);
        CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
        ArrayList<Movie> loadedMovies = new ArrayList<Movie>();
        for (CSVRecord record : parser) {
            String id = record.get("id").trim();
            String title = record.get("title").trim();
            String year = record.get("year").trim();
            String country = record.get("country").trim();
            String genres = record.get("genre").trim();
            String director = record.get("director").trim();
            int minutes = Integer.parseInt(record.get("minutes").trim());
            String poster = record.get("poster").trim();

            loadedMovies.add(new Movie(id, title, year, genres, director,
                country, poster, minutes));
        }

        return loadedMovies;
    }

    public void howManyComedies(){
        int comediesCount = 0;
        for(Movie movie : moviesInfo){
            if(movie.getGenres().toLowerCase().contains("comedy")){
                comediesCount++;
            }
        }
        System.out.println("Comedies found: " +comediesCount + ".");
    }

    public void longerThanNMinutes(int minutes){
        int count = 0;
        for(Movie movie : moviesInfo){
            if(movie.getMinutes() > minutes){
                count++;
            }
        }
        System.out.println("Movies longer than " + minutes + " minutes found: " + count + ".");
    }

    private HashMap<String, Integer> mapDirectorsToMoviesCount(){
        //build a hashmap of directors and movies count
        HashMap<String, Integer> numberOfMoviesByDirector = new HashMap<String, Integer>();;
        for(Movie movie : moviesInfo){
            String[] directors = movie.getDirector().split(",");
            for(String director: directors){
                String directorTrimmed = director.trim(); // get rid of leading or trailing whitespaces
                // if a director not mapped, initialize with 1
                if(!numberOfMoviesByDirector.containsKey(directorTrimmed)){
                    numberOfMoviesByDirector.put(directorTrimmed, 1);
                }
                else{ // if director already mapped, increment movies count
                    int moviesCount = numberOfMoviesByDirector.get(directorTrimmed) + 1;
                    numberOfMoviesByDirector.put(directorTrimmed, moviesCount);
                }
            }
        }
//        for(String director : numberOfMoviesByDirector.keySet()){
//            System.out.println(director + ": " + numberOfMoviesByDirector.get(director));
//        }
        return numberOfMoviesByDirector;
    }

    public void maxNumberOfMovies(){
        //build a hashmap of directors and movies count
        HashMap<String, Integer> numberOfMoviesByDirector = mapDirectorsToMoviesCount();
        // First find the max number of movies by one/several directors
        int maxMovies = 0;
        for(String director : numberOfMoviesByDirector.keySet()){
            int currentCount = numberOfMoviesByDirector.get(director);
            if(currentCount > maxMovies){
                maxMovies = currentCount;
            }
        }
        // since the max number of movies found, make a list of directors who shot that many movies
        ArrayList<String> directorsWithMaxMovies = new ArrayList<String>();
        for(String director : numberOfMoviesByDirector.keySet()){
            int currentCount = numberOfMoviesByDirector.get(director);
            if(currentCount == maxMovies){
                directorsWithMaxMovies.add(director);
            }
        }
        // print out the results
        System.out.println("Max number of movies shot: " + maxMovies);
        System.out.println("Shot by:");
        for(String director : directorsWithMaxMovies){
            System.out.println("\t" + director);
        }
    }

    //====================================================================
    //==================== WORKING WITH RATERS ===========================
     /*
    @param filename - path to a csv file to read from
    - Reads the CSV file,
    - using that data creates objects of class Movie
    - appends the objects to the ArrayList<Movie> moviesInfo
     */
    public ArrayList<Rater> loadRaters(String filename) throws IOException {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        Reader in = new FileReader(filename);
        CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
        for (CSVRecord record : parser) {
            String raterId = record.get("rater_id").trim();
            String movieId = record.get("movie_id").trim();
            double rating = Double.parseDouble(record.get("rating").trim());
            boolean alreadyListed = false; // flag to detected if a rater is already on the list
            if(!raters.isEmpty()) {
                for (Rater rater : raters) {
                    String tempId = rater.getID();
                    if (tempId.equals(raterId)) { // if rater already in the arraylist
                        rater.addRating(movieId, rating);
                        alreadyListed = true;
                    }
                }
            }
            if(!alreadyListed){
                Rater newRater = new Rater(raterId);
                newRater.addRating(movieId, rating);
                raters.add(newRater);
            }
        }
//        System.out.println(raters.size());
        return raters;
    }

    public void printOutRaters(){
        System.out.println("Total raters: " + raters.size());
//        for(Rater rater : raters){
//            System.out.println("==========================");
//            System.out.println("Rater ID: " + rater.getID());
//            System.out.println("items rated: " + rater.getItemsRated());
//        }
    }

    public void numRatingsByUserID(String id){
        for(Rater rater : raters){
            String currentID = rater.getID();
            if(currentID.equals(id)){
                System.out.println("User " + id + " left " + rater.numRatings() + " ratings");
                break;
            }
        }
    }

    private HashMap<String, Integer> mapRatersToNumRatings(){
        //build a hashmap of directors and movies count
        HashMap<String, Integer> numberOfRatingsByRater = new HashMap<String, Integer>();
        for(Rater rater : raters){
            String raterId = rater.getID();
            int numRatings = rater.numRatings();

            if(!numberOfRatingsByRater.containsKey(raterId)){
                numberOfRatingsByRater.put(raterId, numRatings);
            }
        }
//        for(String id : numberOfRatingsByRater.keySet()){
//            System.out.println(id + ": " + numberOfRatingsByRater.get(id));
//        }
        return numberOfRatingsByRater;
    }

    public void maxNumberOfRatings(){
        HashMap<String, Integer> numberOfRatingsByRater = mapRatersToNumRatings();
        // First find the max number of ratings by one/several directors
        int maxRatings = 0;
        for(String raterId : numberOfRatingsByRater.keySet()){
            int currentCount = numberOfRatingsByRater.get(raterId);
            if(currentCount > maxRatings){
                maxRatings = currentCount;
            }
        }
        // since the max number of movies found, make a list of directors who shot that many movies
        ArrayList<String> ratersWithMaxRatings = new ArrayList<String>();
        for(String director : numberOfRatingsByRater.keySet()){
            int currentCount = numberOfRatingsByRater.get(director);
            if(currentCount == maxRatings){
                ratersWithMaxRatings.add(director);
            }
        }
        // print out the results
        System.out.println("Max number of ratings left: " + maxRatings);
        System.out.println("Left by the users with the following ids:");
        for(String raterId : ratersWithMaxRatings){
            System.out.println("\t" + raterId);
        }
    }

    public void numberOfRatingsForMovieID(String movieID){
        int count = 0;
        for(Rater rater : raters){
            if(rater.hasRating(movieID)){
                count++;
            }
        }
        System.out.println("Movie ID: " + movieID + " has " + count + " ratings");
    }

    public void totalMoviesRatedByUsers(){
        ArrayList<String> moviesRated = new ArrayList<String>();
        for(Rater rater : raters){
            for(String movie : rater.getItemsRated()){
                if(!moviesRated.contains(movie)){
                    moviesRated.add(movie);
                }
            }
        }
        System.out.println("Total number of movies rated: " + moviesRated.size());
    }

//    public static void main(String[] args) {
//
//        String filename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";
//        String ratingsFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
//        FirstRatings fr = new FirstRatings(filename, ratingsFilename);
//        try {
////            fr.loadMovies(filename);
////            ArrayList<Movie> info = fr.getMoviesInfo();
////            System.out.println(info.size());
////            fr.howManyComedies();
////            fr.longerThanNMinutes(150);
////            fr.maxNumberOfMovies();
//            fr.loadRaters(ratingsFilename);
////            fr.printOutRaters();
//            fr.numRatingsByUserID("193");
//            fr.maxNumberOfRatings();
//            fr.numberOfRatingsForMovieID("1798709");
//            fr.totalMoviesRatedByUsers();
//        }
//        catch(IOException ioe){
//            System.out.println("Problem loading file");
//        }
//    }
}
