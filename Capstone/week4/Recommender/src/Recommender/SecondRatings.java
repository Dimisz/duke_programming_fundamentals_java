package Recommender;

import java.io.IOException;
import java.util.ArrayList;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
//    private String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";
//    private String ratingsFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";


    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    public ArrayList<Rater> getMyRaters() {
        return myRaters;
    }

    public SecondRatings() {
        // default constructor

        this("/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv",
                "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv");
    }

    public SecondRatings(String moviesFilename, String ratingsFilename){
        FirstRatings fr = new FirstRatings();
        try {
            fr.loadMovies(moviesFilename);
        }
        catch(IOException ioe){
            System.out.println("Unable to load movies from SecondRatings");
        }

        try {
            fr.loadRaters(ratingsFilename);
        }
        catch(IOException ioe){
            System.out.println("Unable to load raters from SecondRatings");
        }
        myMovies = fr.getMovies();
        myRaters = fr.getRaters();
    }

    private double getAverageByID(String movieID, int minimalRaters){
        int ratingsLeft = 0;
        double ratingsSum = 0;
        for(Rater rater : myRaters){
            if(rater.hasRating(movieID)){
                ratingsLeft++;
                ratingsSum += rater.getRating(movieID);
            }
        }
        if(ratingsLeft >= minimalRaters){
            return ratingsSum / ratingsLeft;
        }
        else{
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(Movie movie : myMovies){
            String currentID = movie.getID();
            double currentRating = getAverageByID(currentID, minimalRaters);
            if(currentRating > 0.0) {
                ratings.add(new Rating(currentID, currentRating));
            }
        }
        return ratings;
    }

    public String getTitle(String movieID){
        String title = "Movie with ID " + movieID + " not found";
        for(Movie movie : myMovies){
            String currentID = movie.getID();
            if(currentID.equals(movieID)){
                return movie.getTitle();
            }
        }
        return title;
    }

    public String getID(String movieTitle){
        String id = "NO SUCH TITLE";
        for(Movie movie : myMovies){
            String currentTitle = movie.getTitle();
            if(currentTitle.equals(movieTitle)){
                return movie.getID();
            }
        }
        return id;
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }
}
