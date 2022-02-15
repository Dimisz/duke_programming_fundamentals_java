import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String, ArrayList<String>> mappedFollows;

    public EfficientMarkovModel(int numChars) {
        n = numChars;
        myRandom = new Random();
        mappedFollows = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printHashMapInfo();

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


    private void buildMap(){
        int begin = 0;
        do
        {
            String key = myText.substring(begin, begin + n);
            if (!mappedFollows.containsKey(key))
            {
                ArrayList<String> follows = new ArrayList<String>();
                int index = 0;
                do
                {
                    index = myText.indexOf(key, index);
                    if (index != -1)
                    {
                        if(key.length() + index < myText.length())
                        {
                            follows.add(myText.substring(index + key.length(), index + key.length() + 1));
                            index = index + 1;
                        }
                        else
                        {
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                } while (index != -1);
                mappedFollows.put(key, follows);
            }
            begin += 1;
        } while ((begin + n) <= myText.length());
    }

    public void printHashMapInfo()
    {
        // Print the hashmap only if the hashmap is small
        if (mappedFollows.size() < 50)
        {
            for (String word : mappedFollows.keySet())
            {
                System.out.println("Key: " + word + "   Value: " + mappedFollows.get(word));
            }
        }
        System.out.println("The number of keys: " + mappedFollows.size());
        // Find the key with largest value
        int maxSize = 0;
        ArrayList<String> maxKey = new ArrayList<String>();
        for (String word : mappedFollows.keySet())
        {
            if (mappedFollows.get(word).size() > maxSize)
            {
                maxKey.clear();
                maxSize = mappedFollows.get(word).size();
                maxKey.add(word);
            }
            else if (mappedFollows.get(word).size() == maxSize)
            {
                maxKey.add(word);
            }
        }
        System.out.println("The size of the largest value in the HashMap is : " + maxSize);
        System.out.println("The keys that have the maximum size value: ");
        for (int i = 0; i < maxKey.size(); i++)
        {
            System.out.println(maxKey.get(i));
        }
    }

    public ArrayList<String> getFollows(String key)
    {
        return mappedFollows.get(key);
    }

    public String toString(){
        return "Efficient Markov Model of Order " + n;
    }
}
