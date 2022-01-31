import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonsCount;

    public CodonCount(){
        this.codonsCount = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna){
        codonsCount.clear();
        int remainder = (dna.length() - start) % 3;
        int endIndex = dna.length() - remainder;
        for(int i = start; i < endIndex; i+=3){
            String codon = dna.substring(i, i+3);
            if(codonsCount.keySet().contains(codon)){
                codonsCount.put(codon, codonsCount.get(codon)+1);
            }
            else{
                codonsCount.put(codon, 1);
            }
        }
        System.out.println(codonsCount.size());
    }

    public String getMostCommonCodon(){
        String maxCodon = "None";
        int occurrencesCount = 0;
        for (HashMap.Entry<String, Integer> set :
                codonsCount.entrySet()) {
            if(set.getValue() > occurrencesCount){
                occurrencesCount = set.getValue();
                maxCodon = set.getKey();
            }
        }
        System.out.println("Most common codon: " + maxCodon);
        System.out.println("Occurs: " + occurrencesCount);
        return maxCodon;
    }

    public void printCodonsCounts(int start, int end){
        for (HashMap.Entry<String, Integer> set :
                codonsCount.entrySet()) {
            if(set.getValue() >= start && set.getValue() <= end){
                System.out.println(set.getKey() + ": " + set.getValue());
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        String codonToCheck = fr.asString().toUpperCase().trim();
        buildCodonMap(0, codonToCheck);
        System.out.println("Max occurrences: " + getMostCommonCodon());
        printCodonsCounts(1, 3);

        buildCodonMap(1, codonToCheck);
        System.out.println("Max occurrences: " + getMostCommonCodon());
        printCodonsCounts(1, 3);

        buildCodonMap(2, codonToCheck);
        System.out.println("Max occurrences: " + getMostCommonCodon());
        printCodonsCounts(1, 3);

    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        FileResource fr = new FileResource();
        String codonToCheck = fr.asString().toUpperCase().trim();
        cc.buildCodonMap(0, codonToCheck);
//        cc.buildCodonMap(1, codonToCheck);
//        cc.buildCodonMap(2, codonToCheck);
//        cc.getMostCommonCodon();
        cc.printCodonsCounts(7, 7);
    }
}
