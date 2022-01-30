import edu.duke.FileResource;

import java.util.HashMap;

public class WordFrequenciesMap {

    public void countWords(){
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String w : fr.words()){
            w = w.toLowerCase();
            if(map.keySet().contains(w)){
                map.put(w, map.get(w)+1);
            }
            else{
                map.put(w, 1);
            }
        }
        for(String w : map.keySet()){
            int occurences = map.get(w);
            if(occurences > 500){
                System.out.println(occurences + "\t" + w);
            }
        }

    }
}
