import edu.duke.FileResource;

public class Decrypt {

    public static void main(String[] args) {
//        testEncryptAndDecrypt();
//        testDecrypt();
//   testing halfString method
//        String res1 = halfOfString("Qbkm Zgis", 0);
//        System.out.println(res1);
//        String res2 = halfOfString("Qbkm Zgis", 1);
//        System.out.println(res2);
            testDecryptTwoKeys();
    }

    public static String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }

    public static String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
//        int firstKey = 14;
//        int secondKey = 24;
        System.out.println("First key: " + firstKey);
        System.out.println("Second key: " + secondKey);

        CaesarCipher cc = new CaesarCipher();
        String firstHalfDecrypted = cc.encrypt(firstHalf, 26-firstKey);
        String secondHalfDecrypted = cc.encrypt(secondHalf, 26-secondKey);

//        String firstHalfDecrypted = cc.encrypt(firstHalf, 26-2);
//        String secondHalfDecrypted = cc.encrypt(secondHalf, 26-20);
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < firstHalfDecrypted.length(); i++){
            result.append(firstHalfDecrypted.charAt(i));
            if(i <= secondHalfDecrypted.length()-1){
                result.append(secondHalfDecrypted.charAt(i));
            }
        }
        return result.toString();
    }

    public static int[] countLetters(String message){
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabetLower.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public static String halfOfString(String message, int start){
        StringBuilder halfString = new StringBuilder();
        int remainder = start % 2 == 0 ? 0 : 1;
        for(int i = start; i < message.length(); i++){
            if(i % 2 == remainder){
                halfString.append(message.charAt(i));
                //halfString.setCharAt((i / 2), message.charAt(i));
            }
        }
        return halfString.toString();
    }

    public static int maxIndex(int[] array){
        int maxVal = array[0];
        int max = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] > maxVal){
                maxVal = array[i];
                max = i;
            }
        }
        return max;
    }

    public static int getKey(String message){
        int[] freqs = countLetters(message);
        int maxDex = maxIndex(freqs);

        int key = maxDex - 4;
        if(maxDex < 4){
            key = 26 - (4 - maxDex);
        }

        return key;
    }

    public static void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("=========Decrypting========");
        String decrypted = decrypt(message);
        System.out.println(decrypted);
    }


    public static void testEncryptAndDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("=========Encrypting========");
        String encrypted = cc.encrypt(message, key);
        System.out.println(encrypted);
        System.out.println("=========Decrypting========");
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }

    public static void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String message = fr.asString();
//        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("=========Decrypting========");
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }


}
