import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            slice.append(message.charAt(i));
        }
        String result = slice.toString();
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

    public void breakVigenereWithKey (int keyLength, char mostCommon) {
        FileResource fr = new FileResource();
        String text = fr.asString();
        int[] key = tryKeyLength(text, keyLength, mostCommon);
        for(int i : key) {
            System.out.print(i + " ");
        }
        VigenereCipher vc = new VigenereCipher(key);
        String message = vc.decrypt(text);
        System.out.println(message);
    }

    //====Part 2 of the week===================
    public HashSet<String> readDictionary(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = new HashSet<String>();
        for(String word : fr.lines()){
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dict){
        String mostRealDecryption = "Not decrypted yet";
        int maxWords = 0;
        int usedKLength = -1;
        for(int i = 1; i < 101; i++){
            int[] key = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int wordCounts = countWords(decrypted, dict);
            if(wordCounts > maxWords){
                maxWords = wordCounts;
                mostRealDecryption = decrypted;
                usedKLength = i;
            }
            if(i == 38){
                System.out.println("Key: " + i);
                System.out.println("Valid words: " + wordCounts);
            }
        }
        System.out.println("Valid words: " + maxWords);
        System.out.println("KLength used: " + usedKLength);
        return mostRealDecryption;
    }

    public void breakVigenereWithoutKey() {
        HashSet<String> dict = readDictionary();
        FileResource fr = new FileResource();
        String text = fr.asString();
        String result = breakForLanguage(text, dict);
        System.out.println(result);
    }



    //=============TESTS=======================
    //=========================================
    public static void testTryKeyLength(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(msg, 5, 'e');
        for(int i : keys){
            System.out.print(i + ", ");
        }
    }

    public static void testSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println("adgjm :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 1, 3);
        System.out.println("behk :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 2, 3);
        System.out.println("cfil :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 0, 4);
        System.out.println("aeim :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 2, 4);
        System.out.println("cgk :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 3, 4);
        System.out.println("dhl :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 0, 5);
        System.out.println("afk :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 1, 5);
        System.out.println("bgl :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 2, 5);
        System.out.println("chm :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 3, 5);
        System.out.println("di :\t" + slice);

        slice = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println("ej :\t" + slice);
    }

    public static void testBreakVigenereWithKey(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenereWithKey(4, 'e');
    }


    public static void testBreakVigenereWithoutKey(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenereWithoutKey();
    }

    //=============================================
    //=============================================
    public static void main(String[] args) {
//        testSliceString();
//        testTryKeyLength();
//        testBreakVigenereWithKey();
        testBreakVigenereWithoutKey();
    }
    
}
