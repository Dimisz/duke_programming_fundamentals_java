package Recommender;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public static void printAverageRatings(int minRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        ArrayList<Rating> ratings = thirdRatings.getAverageRatings(minRaters);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            System.out.println(rating.getValue() + " " + currentTitle);
        }
    }

    public static void printAverageRatingsByYear(int minRaters, int yearToFilter){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        Filter yearFilter = new YearAfterFilter(yearToFilter);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, yearFilter);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            int currentYear = MovieDatabase.getYear(currentID);
            System.out.println(rating.getValue() + " " + currentYear + " " + currentTitle);
        }
    }



    public static void printAverageRatingsByGenre(int minRaters, String genreToFilter){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        Filter genreFilter = new GenreFilter(genreToFilter);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, genreFilter);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            String currentGenres = MovieDatabase.getGenres(currentID);
            System.out.println(rating.getValue() + " " + currentTitle + " " + currentGenres);
        }
    }


    public static void printAverageRatingsByMinutes(int minRaters, int minMinutes, int maxMinutes){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, minutesFilter);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            int currentMinutes = MovieDatabase.getMinutes(currentID);
            System.out.println(rating.getValue() + " Time: " + currentMinutes + " " + currentTitle);
        }
    }


    public static void printAverageRatingsByDirectors(int minRaters, String directors){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        Filter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, directorsFilter);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            String currentDirectors = MovieDatabase.getDirector(currentID);
            System.out.println(rating.getValue() + " " + currentTitle + "\n" + currentDirectors);
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre(int minRaters, int year, String genre){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, allFilters);
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


    public static void printAverageRatingsByDirectorsAndMinutes(int minRaters, String directors, int minMinutes, int maxMinutes){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmoviesfull.csv";

        ThirdRatings thirdRatings = new ThirdRatings(ratersFilename);
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        MovieDatabase.initialize(moviesFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter(directors));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minRaters, allFilters);
        Collections.sort(ratings);
        System.out.println("found " + ratings.size() + " movies");
        for(Rating rating : ratings){
            String currentID = rating.getItem();
            String currentTitle = MovieDatabase.getTitle(currentID);
            int movieLength = MovieDatabase.getMinutes(currentID);
            String currentDirectors = MovieDatabase.getDirector(currentID);
            System.out.println(rating.getValue() + " Time: " + movieLength + " " +  currentTitle + "\n" + currentDirectors);
        }
    }


    public static void main(String[] args) {
//        MovieRunnerWithFilters.printAverageRatings(35);
//        MovieRunnerWithFilters.printAverageRatingsByYear(20,2000);
//        MovieRunnerWithFilters.printAverageRatingsByGenre(20, "Comedy");
//        MovieRunnerWithFilters.printAverageRatingsByMinutes(5, 105, 135);
//        MovieRunnerWithFilters.printAverageRatingsByDirectors(4, "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
//        MovieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");
        MovieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes(3, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 90, 180);
    }
}
