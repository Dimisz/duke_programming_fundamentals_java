import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Title = q1.getInfo();
        String q2Title = q2.getInfo();

        String q1LastWord = q1Title.substring(q1Title.lastIndexOf(" "));
        String q2LastWord = q2Title.substring(q2Title.lastIndexOf(" "));
        int titleComparison = q1LastWord.compareTo(q2LastWord);
        if(titleComparison == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return titleComparison;
    }

}
