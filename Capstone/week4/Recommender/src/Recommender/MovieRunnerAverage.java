package Recommender;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public static void printAverageRatings(){
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("Number of movies: " + secondRatings.getMovieSize());
        System.out.println("Number of raters: " + secondRatings.getRaterSize());
        ArrayList<Rating> ratings = secondRatings.getAverageRatings(12);
        Collections.sort(ratings);
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = secondRatings.getTitle(currentID);
            System.out.println(rating.getValue() + " " + currentTitle);
        }
    }

    public static void getAverageRatingOneMovie(){
        int ratingsLeft = 0;
        double ratingsSum = 0;
        SecondRatings secondRatings = new SecondRatings();
        String currentTitle = "Vacation";
        ArrayList<Rater> raters = secondRatings.getMyRaters();
        String movieID = secondRatings.getID(currentTitle);
        for(Rater rater : raters){
            if(rater.hasRating(movieID)){
                ratingsLeft++;
                ratingsSum += rater.getRating(movieID);
            }
        }
        if(ratingsLeft > 0) {
            double average = ratingsSum / ratingsLeft;
            System.out.println("Average rating of " + currentTitle + " is " + average);
        }
        else{
            System.out.println(currentTitle + "has no ratings yet");
        }

    }

    public static void main(String[] args) {
        printAverageRatings();
        getAverageRatingOneMovie();
 }
}
