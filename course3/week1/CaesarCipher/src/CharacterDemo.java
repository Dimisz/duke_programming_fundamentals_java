public class CharacterDemo {
    public static void main(String[] args) {
        digitTest();
    }
    public static void digitTest(){
        String test = "ABCd12D34VV5kko,,,9i";
        int lettersCount = 0;
        int digitsCount = 0;
        for(int i = 0; i < test.length(); i++){
            char ch = test.charAt(i);
            if(Character.isAlphabetic(ch)){
                System.out.println(ch + " is alphabetic");
                lettersCount++;
            }
            else if(Character.isDigit(ch)){
                System.out.println(ch + " is a digit");
                digitsCount++;
            }
        }
        System.out.println("Total letter: " + lettersCount);
        System.out.println("Total digits: " + digitsCount);
        System.out.println("Punctuation: " + (test.length()-(digitsCount+lettersCount)));
    }
}
