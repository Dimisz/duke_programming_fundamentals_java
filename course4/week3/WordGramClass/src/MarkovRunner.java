
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
//        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
//        }
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(5);

        runModel(markovWord, st, 200, 844);
    }

    public void runEfMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);

        runModel(markovWord, st, 200, 65);
        //markovWord.printHashMapInfo();

    }

    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();

        EfficientMarkovWord effMarkov = new EfficientMarkovWord(5);
//        String st = "this is a test yes this is really a test yes a test this is wow";
        runModel(effMarkov, st, 50, 531);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        System.out.println("Running MarkowWord....");
        MarkovWord markovWord = new MarkovWord(2);
        for(int i = 0; i < 3; i++) {
            runModel(markovWord, st, 100, 42);
        }
        System.out.println("Runnning efficient markov...");
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        for(int i = 0; i < 3; i++) {
            runModel(efficientMarkovWord, st, 100, 42);
        }
    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
//        mr.runMarkov();
        mr.testHashMap();
//        mr.compareMethods();
//        mr.runEfMarkov();
    }
}
