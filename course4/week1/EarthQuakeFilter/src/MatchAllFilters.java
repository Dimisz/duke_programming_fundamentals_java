import java.util.ArrayList;

public class MatchAllFilters implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilters(){
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

    public String getName(){
        String name = "";
        for(Filter f : filters){
            name += f.getClass().getName() + " ";
        }
        return name;
    }
}
