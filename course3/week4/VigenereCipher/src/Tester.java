import edu.duke.FileResource;

public class Tester {

    public static void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String text = fr.asString();
        String encrypted = cc.encrypt(text);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("\n" + decrypted);
    }

    public static void testCaesarCrackerEnglish(){
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println(text);
        int key = cc.getKey(text);
        System.out.println("\nKey: " + key);
        String decrypted = cc.decrypt(text);
        System.out.println(decrypted);
    }

    public static void testCaesarCrackerNonEnglish(char commonChar){
        CaesarCracker cc = new CaesarCracker(commonChar);
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println(text);
        int key = cc.getKey(text);
        System.out.println("\nKey: " + key);
        String decrypted = cc.decrypt(text);
        System.out.println(decrypted);
    }

    public static void testVigenereCipher(int[] key){
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println(text);

        String encrypted = vc.encrypt(text);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("\n" + decrypted);
    }

    public static void main(String[] args) {
//        testCaesarCipher();
//        testCaesarCrackerEnglish();
//        testCaesarCrackerNonEnglish('a');
        int[] key = {17, 14, 12, 4};
        testVigenereCipher(key);
    }
}
