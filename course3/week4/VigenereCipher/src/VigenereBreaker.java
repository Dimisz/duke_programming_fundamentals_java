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
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
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

    public static void main(String[] args) {
        testSliceString();
    }
    
}
