import java.util.ArrayList;

public class DirectorsFilter implements Filter{
    private String[] directors;

    public DirectorsFilter(String listOfDirectors) {
        directors = listOfDirectors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        String currentDirectors = MovieDatabase.getDirector(id);
        for(String director : directors){
            if(currentDirectors.toLowerCase().contains(director.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
