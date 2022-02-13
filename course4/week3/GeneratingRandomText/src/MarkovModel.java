import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int n;

    public MarkovModel(int numChars) {
        n = numChars;
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    private ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;

        while(pos < myText.length()){
            int start = myText.indexOf(key, pos);
            if(start == -1){
                break;
            }
            if(start + key.length() >= myText.length()-1){
                break;
            }
            String next = myText.substring(start+key.length(), start+key.length()+1);
            follows.add(next);
            pos = start+key.length();
        }
        return follows;
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        for(int k=0; k < numChars-n; k++){
            ArrayList<String> follows = getFollows(key);
//            System.out.println("key " + key + " " + follows);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
//            System.out.println("key: " + key.length() + " next: " + next.length());
            key = key.substring(1) + next;

        }

        return sb.toString();
    }
}
