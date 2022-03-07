import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public static void printAverageRatings(int minRaters){
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings_short.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmovies_short.csv";

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
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings_short.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmovies_short.csv";

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
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings_short.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmovies_short.csv";

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
        String ratersFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratings_short.csv";
        String moviesFilename = "/Users/mbpro/Downloads/StepOneStarterProgram/data/ratedmovies_short.csv";

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

    public static void main(String[] args) {
//        MovieRunnerWithFilters.printAverageRatings(1);
//        MovieRunnerWithFilters.printAverageRatingsByYear(1,2000);
//        MovieRunnerWithFilters.printAverageRatingsByGenre(1, "Crime");
        MovieRunnerWithFilters.printAverageRatingsByMinutes(1, 110, 170);
    }
}
