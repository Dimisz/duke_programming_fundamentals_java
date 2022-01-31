import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();

        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public int findIndexOfMax(){
        if(myFreqs.size() > 0){
            int max = myFreqs.get(0);
            int indexOfMax = 0;
            for(int i = 0; i < myFreqs.size(); i++){
                if(myFreqs.get(i) > max){
                    max = myFreqs.get(i);
                    indexOfMax = i;
                }
            }
            return indexOfMax;
        }
        return -1;
    }

    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("Index of max: " + max);
        System.out.println("Most frequent word: " + myWords.get(max));
        System.out.println("Occurred " + myFreqs.get(max) + " times");
    }

    public static void main(String[] args) {
        WordFrequencies wordFreqs = new WordFrequencies();
        wordFreqs.tester();
    }
}
