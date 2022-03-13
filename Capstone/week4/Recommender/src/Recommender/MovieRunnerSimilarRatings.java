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
//        printSimilarRatings("65", 20, 5);
//        printSimilarRatingsByGenre("Action","65", 20, 5);
//        printSimilarRatingsByDirector("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone","1034", 10, 3);
//        printSimilarRatingsByGenreAndMinutes("Adventure", 100, 200, "65", 10, 5);
//        printSimilarRatingsByYearAfterAndMinutes(2000, 80, 100, "65",10, 5);
    }
}
