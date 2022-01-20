import edu.duke.FileResource;

public class CaesarCipher {

    public static void main(String[] args) {
        // testCaesar();
//        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
//        System.out.println(encrypt("First Legion", 17));
//        System.out.println(encryptTwoKeys("First Legion", 23, 17));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetUpperKey1 = alphabetUpper.substring(key1)+alphabetUpper.substring(0, key1);
        String shiftedAlphabetLowerKey1 = alphabetLower.substring(key1)+alphabetLower.substring(0, key1);
        String shiftedAlphabetUpperKey2 = alphabetUpper.substring(key2)+alphabetUpper.substring(0, key2);
        String shiftedAlphabetLowerKey2 = alphabetLower.substring(key2)+alphabetLower.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabetUpper.indexOf(currChar);
            if (idx != -1) {
                if(i % 2 == 0){
                    char newChar = shiftedAlphabetUpperKey1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    char newChar = shiftedAlphabetUpperKey2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }

            }

            int idxLower = alphabetLower.indexOf(currChar);
            if (idxLower != -1) {
                if(i % 2 == 0){
                    char newChar = shiftedAlphabetLowerKey1.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    char newChar = shiftedAlphabetLowerKey2.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }

            }
        }
        return encrypted.toString();
    }

    public static String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetUpper = alphabetUpper.substring(key)+alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key)+alphabetLower.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabetUpper.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabetUpper.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }

            int idxLower = alphabetLower.indexOf(currChar);
            if (idxLower != -1) {
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public static void testCaesar(){
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}
