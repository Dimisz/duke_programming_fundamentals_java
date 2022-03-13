package Recommender;

import java.io.IOException;
import java.util.ArrayList;

public class FourthRatings {

    //    private String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";
    //private String ratingsFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";



    private double getAverageByID(String movieID, int minimalRaters){
        int ratingsLeft = 0;
        double ratingsSum = 0;
        //RaterDatabase.addRatings(ratingsFilename);
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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

        private double dotProduct(Rater me, Rater r){
            double similarityRate = 0.0;
            ArrayList<String> myRatings = me.getItemsRated();
            ArrayList<String> rRatings = r.getItemsRated();
            for(String movieId : myRatings){
                if(rRatings.contains(movieId)){
                    similarityRate += (me.getRating(movieId) - 5) * (r.getRating(movieId) - 5);
                }
            }
            return similarityRate;
        }

        private ArrayList<Rating> getSimilarities(String id){
            ArrayList<Rating> similarities = new ArrayList<Rating>();
            ArrayList<Rater> raters = RaterDatabase.getRaters();
            Rater me = RaterDatabase.getRater(id);
            for(Rater rater : raters){
                String raterId = rater.getID();
                if(!raterId.equals(id)){
                    Rating currentSimilarity = new Rating(raterId, dotProduct(me, rater));
                    similarities.add(currentSimilarity);
                }
            }
            return similarities;
        }


}
