package Recommender;

import java.sql.SQLOutput;
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

    public static void printSimilarRatings(String raterId, int numSimilarRaters, int minimalRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(raterId, numSimilarRaters, minimalRaters);
        for(Rating rating : similarRatings){
            String movieId = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieId);
            System.out.println(movieTitle + " : " + rating.getValue());
            break;
        }

    }

    public static void printSimilarRatingsByGenre(String genre, String raterId, int numSimilarRaters, int minimalRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, new GenreFilter(genre));
        for(Rating rating : similarRatings){
            String movieId = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieId);
            System.out.println(movieTitle + " : " + rating.getValue());
            break;
        }

    }


    public static void printSimilarRatingsByDirector(String director, String raterId, int numSimilarRaters, int minimalRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, new DirectorsFilter(director));
        for(Rating rating : similarRatings){
            String movieId = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieId);
            System.out.println(movieTitle + " : " + rating.getValue());
            break;
        }

    }


    public static void printSimilarRatingsByGenreAndMinutes(String genre, int minMinutes, int maxMinutes, String raterId, int numSimilarRaters, int minimalRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allFilters);
        for(Rating rating : similarRatings){
            String movieId = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieId);
            System.out.println(movieTitle + " : " + rating.getValue());
            //break;
        }

    }


    public static void printSimilarRatingsByYearAfterAndMinutes(int year, int minMinutes, int maxMinutes, String raterId, int numSimilarRaters, int minimalRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        FourthRatings fourthRatings = new FourthRatings();

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize(ratersFilename);
        System.out.println("Number of ratings: " + RaterDatabase.size());

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allFilters);
        for(Rating rating : similarRatings){
            String movieId = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieId);
            System.out.println(movieTitle + " : " + rating.getValue());
            //break;
        }

    }


    public static void main(String[] args) {
//        printAverageRatings(5);
//        printSimilarRatings("71", 20, 5);
//        printSimilarRatingsByGenre("Mystery","964", 20, 5);
//        printSimilarRatingsByDirector("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh","120", 10, 2);
//        printSimilarRatingsByGenreAndMinutes("Drama", 80, 160, "168", 10, 3);
        printSimilarRatingsByYearAfterAndMinutes(1975, 70, 200, "314",10, 5);
    }
}
