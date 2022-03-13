package Recommender;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public static void printAverageRatings(int minRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(minRaters);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            System.out.println(rating.getValue() + " " + currentTitle);
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre(int minRaters, int year, String genre){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(minRaters, allFilters);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            int currentYear = MovieDatabase.getYear(currentID);
            String currentGenre = MovieDatabase.getGenres(currentID);
            System.out.println(rating.getValue() + " " + currentYear + " " +  currentTitle + "\n" + currentGenre);
        }
    }


    public static void main(String[] args) {
        printAverageRatings(5);
    }
}
