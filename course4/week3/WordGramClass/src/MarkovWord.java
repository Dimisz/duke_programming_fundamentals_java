import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        String[] keyArray = new String[myOrder];
        for(int i = 0; i < myOrder; i++){
            String word = myText[index + i];
            keyArray[i] = word;
            sb.append(word);
            sb.append(" ");
       }
//        System.out.println(keyArray); // debug
       WordGram key = new WordGram(keyArray, 0, myOrder);
//        System.out.println("key: " + key); // debug
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
//			System.out.println(key + ": " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
//            System.out.println("next: " + next); // debug
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
//            System.out.println("shiffted key: " + key); // debug
//            System.out.println(sb.toString());
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start){
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = myOrder;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
//        WordGram first = list.get(0);
//        System.out.println("checking "+first);
        for(int k=start; k < list.size(); k++){
            //if (first == list.get(k)) {
            if (target.equals(list.get(k))) {
//                System.out.println("matched at "+k+" "+list.get(k));
                return k;
            }
        }
        return -1;
    }

    public void testIndexOf(){
        String[] testArray = "this is just a test yes this is a simple test".split("\\s+");
        System.out.print("Looking for 'this is just a' starting at 0 : ");
        String[] targetArr = "just a test yes".split("\\s+");
        WordGram target = new WordGram(targetArr, 0, 4);
        System.out.println(indexOf(testArray, target, 0));
        System.out.println("Next word: " + testArray[indexOf(testArray, target, 0) + myOrder]);

    }

    private ArrayList<String> getFollows(WordGram kGram) {
//        System.out.println("Inside get follows");
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length - myOrder){
//            System.out.println("pos:" + pos); // debug
            int start = indexOf(myText, kGram, pos);
//            System.out.println("start:"+start);
            if(start == -1){
                break;
            }
            if(start+myOrder > myText.length - 1){
                break;
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start + 1;
//            System.out.println(follows);
        }
//        System.out.println(follows);
        return follows;
    }

    public static void main(String[] args) {
        MarkovWord mWord = new MarkovWord(4);
        mWord.testIndexOf();
    }
}
