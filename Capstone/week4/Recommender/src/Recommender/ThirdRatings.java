package Recommender;

import java.io.IOException;
import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
//    private String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";
//    private String ratingsFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";

    public ArrayList<Rater> getMyRaters() {
        return myRaters;
    }

    public ThirdRatings() {
        // default constructor
        this("/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv");
    }

    public ThirdRatings(String ratingsFilename){
        FirstRatings fr = new FirstRatings();

        try {
            myRaters = fr.loadRaters(ratingsFilename);
        }
        catch(IOException ioe){
            System.out.println("Unable to load raters from SecondRatings");
        }

//        myRaters = fr.getRaters();
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : movies){
            double currentRating = getAverageByID(movie, minimalRaters);
            if(currentRating > 0.0) {
                ratings.add(new Rating(movie, currentRating));
            }
        }
        return ratings;
    }



    public int getRaterSize(){
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie : movies){
            double currentRating = getAverageByID(movie, minimalRaters);
            if(currentRating > 0.0) {
                ratings.add(new Rating(movie, currentRating));
            }
        }
        return ratings;
    }
}
