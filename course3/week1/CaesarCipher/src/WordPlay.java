public class WordPlay {

    public static void main(String[] args) {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static boolean isVowel(char ch){
        char lowerChar = Character.toLowerCase(ch);
        if (lowerChar == 'a' || lowerChar == 'e' || lowerChar == 'i' ||
        lowerChar == 'o' || lowerChar == 'u'){
            return true;
        }
        else{
            return false;
        }
    }

    public static String replaceVowels(String phrase, char ch){
        StringBuilder result = new StringBuilder(phrase);
        for(int i = 0; i < result.length(); i++){
            if(isVowel(result.charAt(i))){
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }

    public static String emphasize(String input, char ch){

        StringBuilder result = new StringBuilder(input);
        for (int i = 0; i < result.length(); i++){
            if(Character.toLowerCase(result.charAt(i)) == ch){
                if(i % 2 == 0){
                    result.setCharAt(i, '*');
                }
                else{
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }
}
