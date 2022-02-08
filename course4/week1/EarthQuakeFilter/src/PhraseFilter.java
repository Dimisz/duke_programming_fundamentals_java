public class PhraseFilter implements Filter{
    private String searchLocation;
    private String searchPhrase;

    public PhraseFilter(String searchLocation, String searchPhrase){
        this.searchLocation = searchLocation;
        this.searchPhrase = searchPhrase;
    }

    public boolean satisfies(QuakeEntry qe) {
        if(searchLocation.equals("start")){
            return qe.getInfo().startsWith(searchPhrase);
        }
        else if(searchLocation.equals("end")){
            return qe.getInfo().endsWith(searchPhrase);
        }
        else if(searchLocation.equals("any")){
            return qe.getInfo().contains(searchPhrase);
        }
        return false;
    }
}
