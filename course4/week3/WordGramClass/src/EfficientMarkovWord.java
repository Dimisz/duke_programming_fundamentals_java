import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel{

        private String[] myText;
        private Random myRandom;
        private int myOrder;
        private HashMap<String, ArrayList<String>> mappedFollows;

        public EfficientMarkovWord(int order) {
            myOrder = order;
            myRandom = new Random();
        }

    private void buildMap(){
        mappedFollows = new HashMap<String, ArrayList<String>>();
        WordGram kGram = new WordGram(myText, 0, myOrder);
        String keyString = kGram.getMyWordsString();
        for(int i = 0; i < myText.length - myOrder; i++){
            String follow = myText[i + kGram.length()];
            if (mappedFollows.containsKey(keyString)) {
                mappedFollows.get(keyString).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                mappedFollows.put(keyString, list);
            }
            kGram = kGram.shiftAdd(follow);
            keyString = kGram.getMyWordsString();
        }
        /*
        for (int k=0; k<myText.length-myOrder; k++) {
            // String key = myText.substring(k, k+myNumber);;
            WordGram kGram = new WordGram(myText, k, myOrder);
            String follow = myText[k+kGram.length()];
            if (mappedFollows.containsKey(kGram)) {
                mappedFollows.get(kGram).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                mappedFollows.put(kGram, list);
            }
        }
        WordGram kGram = new WordGram(myText, myText.length-myOrder, myOrder);
        if (!(mappedFollows.containsKey(kGram))){
            ArrayList<String> list = new ArrayList<String>();
            mappedFollows.put(kGram, list);
        }
         */
        /*
        int begin = 0;
        for(int i = 0; i < )
        System.out.println("length: " + myText.length);
        while ((begin + myOrder) <= myText.length)
        {
            //System.out.println("outer while");
           // System.out.println("==============\nbegin : " + begin);
            String[] keyArray = new String[myOrder];
            for(int i = 0; i < myOrder; i++){
                String word = myText[begin + i];
                keyArray[i] = word;
            }

            WordGram keyGram = new WordGram(keyArray, 0, myOrder);
            //String currentString = keyGram.getMyWordsString();
            if(!mappedFollows.containsKey(keyGram))
            {
                ArrayList<String> follows = new ArrayList<String>();
                int pos = 0;
                do{
                    System.out.println(pos);
                    int start = indexOf(myText, keyGram, pos);
                    if(start == -1){
                        break;
                    }

                    if(start+myOrder > myText.length-1){
                        break;
                    }
                    String next = myText[start+myOrder];
                    follows.add(next);
                    pos = start + 1;
                }while(pos + myOrder < myText.length);
                mappedFollows.put(currentString, follows);
            }
            begin++;
        }
        */
    }

    public void printHashMapInfo()
    {
        // Print the hashmap only if the hashmap is small
//        if (mappedFollows.size() < 50)
//        {
//            for(WordGram hash : mappedFollows.keySet())
//            {
//                System.out.println("Key: " + hash + "   Value: " + mappedFollows.get(hash));
//            }
//        }
        System.out.println("The number of keys: " + mappedFollows.size());
        int maxSize = 0;

        ArrayList<String> maxKey = new ArrayList<String>();
        for(String hash : mappedFollows.keySet())
        {
            if (mappedFollows.get(hash).size() > maxSize)
            {
                maxKey.clear();
                maxSize = mappedFollows.get(hash).size();
                maxKey.add(hash.toString());
            }
            else if (mappedFollows.get(hash).size() == maxSize)
            {
                maxKey.add(hash.toString());
            }
        }
        System.out.println("The size of the largest value in the HashMap is : " + maxSize);
        System.out.println("The keys that have the maximum size value: ");
        for (int i = 0; i < maxKey.size(); i++)
        {
            System.out.println(maxKey.get(i));
        }
    }

    public ArrayList<String> getFollows(WordGram kGram)
    {
       String currentStr = kGram.getMyWordsString();
        return mappedFollows.get(currentStr);
    }


        public void setRandom(int seed) {
            myRandom = new Random(seed);
        }

        public void setTraining(String text){
            myText = text.split("\\s+");
            buildMap();
            printHashMapInfo();
        }

        public String getRandomText(int numWords){
            //printHashMapInfo();


            StringBuilder sb = new StringBuilder();
            int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
            String[] keyArray = new String[myOrder];
            for(int i = 0; i < myOrder; i++){
                String word = myText[index + i];
                keyArray[i] = word;
                sb.append(word);
                sb.append(" ");
            }
            WordGram key = new WordGram(keyArray, 0, myOrder);
            System.out.println("initial key: " + key);
            for(int k=0; k < numWords-myOrder; k++){
                ArrayList<String> follows = getFollows(key);
                System.out.println(key + ": " + follows);
                if(follows == null){
                    break;
                }
//                if (follows.size() == 0) {
//                    break;
//                }
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                sb.append(next);
                sb.append(" ");
                key = key.shiftAdd(next);
                System.out.println("shifted key: " + key);

            }

            return sb.toString().trim();


        }

        private int indexOf(String[] words, WordGram target, int start){
            ArrayList<WordGram> list = new ArrayList<WordGram>();
            int size = myOrder;
            for(int index = 0; index <= words.length - target.length(); index += 1) {
                WordGram wg = new WordGram(words, index, size);
                if (wg.equals(target)) {
                    return index;
                }
            }
                /*
                list.add(wg);
            }

            for(int k=start; k < list.size(); k++){
                if (target.equals(list.get(k))) {
                    return k;
                }
            }

                 */
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


        public static void main(String[] args) {
            MarkovWord mWord = new MarkovWord(4);
            mWord.testIndexOf();
        }


}
