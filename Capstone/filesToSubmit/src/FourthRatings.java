import java.util.ArrayList;
import java.util.Collections;

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
                    double similarity = dotProduct(me, rater);
                    if(similarity > 0) {
                        Rating currentSimilarity = new Rating(raterId, similarity);
                        similarities.add(currentSimilarity);
                    }
                }
            }
            Collections.sort(similarities, Collections.reverseOrder());
            return similarities;
        }



public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {

    ArrayList<Rating> result = new ArrayList<Rating>();
    ArrayList<Rating> raters = getSimilarities(id);

    //Iterates over all Movie IDs of movies in MovieDatabase
    for(String movie : MovieDatabase.filterBy(new TrueFilter())) {
        double totalWeightedRating = 0.0;
        int numTopRaters = 0;
        //Iterates over the top numSimilarRaters ratings in raters
        for(int i = 0 ; i < numSimilarRaters ; i ++) {
            //Stores the rating at index i
            Rating raterSimilarityRating = raters.get(i);
            //Stores the Rater whose rating is at index i
            Rater rater = RaterDatabase.getRater(raterSimilarityRating.getItem());

            if(RaterDatabase.getRater(id).hasRating(movie)) continue;
            //If rater has rated movie with id movie then his weightd rating is added to
            //totalWightedRating and numTopRaters is incremented.
            if(rater.hasRating(movie)) {
                totalWeightedRating += (rater.getRating(movie) ) * raterSimilarityRating.getValue();
                numTopRaters ++;
            }
        }
        //If numSimilarRaters or more topRaters have rated the movie, it is
        //added to result.
        if(numTopRaters >= minimalRaters) {
            result.add(new Rating(movie, totalWeightedRating/numTopRaters));
        }
    }
    Collections.sort(result, Collections.reverseOrder());
    //System.out.println(result);
    return result;
}


    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){

        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> raters = getSimilarities(id);

        //Iterates over all Movie IDs of movies in MovieDatabase
        for(String movie : MovieDatabase.filterBy(filterCriteria)) {
            double totalWeightedRating = 0.0;
            int numTopRaters = 0;
            //Iterates over the top numSimilarRaters ratings in raters
            for(int i = 0 ; i < numSimilarRaters ; i ++) {
                //Stores the rating at index i
                Rating raterSimilarityRating = raters.get(i);
                //Stores the Rater whose rating is at index i
                Rater rater = RaterDatabase.getRater(raterSimilarityRating.getItem());

                if(RaterDatabase.getRater(id).hasRating(movie)) continue;
                //If rater has rated movie with id movie then his weightd rating is added to
                //totalWightedRating and numTopRaters is incremented.
                if(rater.hasRating(movie)) {
                    totalWeightedRating += (rater.getRating(movie) ) * raterSimilarityRating.getValue();
                    numTopRaters ++;
                }
            }
            //If numSimilarRaters or more topRaters have rated the movie, it is
            //added to result.
            if(numTopRaters >= minimalRaters) {
                result.add(new Rating(movie, totalWeightedRating/numTopRaters));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        //System.out.println(result);
        return result;
    }





}
