import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFile {
    private HashMap<String, ArrayList<String>> wordsInFiles;

    public WordsInFile(){
        this.wordsInFiles = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        for(String s : fr.words()){

           if(wordsInFiles.keySet().contains(s)){
               wordsInFiles.get(s).add(filename);
           }
           else{
               ArrayList<String> al = new ArrayList<String>();
               al.add(filename);
               wordsInFiles.put(s, al);
           }
        }
    }

    public void buildWordFileMap(){
        wordsInFiles.clear();
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public int maxNumber(){
        int maxNum = 0;

        for (HashMap.Entry<String, ArrayList<String>> set :
                wordsInFiles.entrySet()) {
            int currentLength = set.getValue().size();
            maxNum = currentLength > maxNum ? currentLength : maxNum;
        }
        return maxNum;
    }

    public ArrayList<String> wordsInNumFiles(int occurrences){
        ArrayList<String> words = new ArrayList<String>();
        for (HashMap.Entry<String, ArrayList<String>> set :
                wordsInFiles.entrySet()) {
            int currentLength = set.getValue().size();
            if(currentLength == occurrences){
                words.add(set.getKey());
            }
        }
        return words;
    }

    public void printFilesIn(String word){
        if(wordsInFiles.keySet().contains(word)){
            for(String s : wordsInFiles.get(word)){
                System.out.print(s + " ");
            }
        }
        else{
            System.out.println(word + " not found in keys");
        }
    }

    public void printHashMap(){
        for (HashMap.Entry<String, ArrayList<String>> set :
                wordsInFiles.entrySet()) {

            System.out.print(set.getKey() + ":\t");
            for(String s: set.getValue()){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WordsInFile wf = new WordsInFile();
        wf.buildWordFileMap();
        wf.printHashMap();
        System.out.println(wf.maxNumber());
        System.out.println("\n=====Words in 3 files=====");
        ArrayList<String> al1 = wf.wordsInNumFiles(3);
        for(String s : al1){
            System.out.println(s + " ");
        }
        System.out.println("\n=====Words in 2 files=====");
        ArrayList<String> al2 = wf.wordsInNumFiles(2);
        for(String s : al2){
            System.out.println(s + " ");
        }
        System.out.println("\n=====Print files in cats=====");
        wf.printFilesIn("cats");
    }
}
