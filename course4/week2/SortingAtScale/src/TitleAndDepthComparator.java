import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
//    Location fromWhere;
//
//    public TitleAndDepthComparator(Location where) {
//        fromWhere = where;
//    }

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int titleComparison = q1.getInfo().compareTo(q2.getInfo());
        if(titleComparison == 0){
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
        return titleComparison;
    }

}
