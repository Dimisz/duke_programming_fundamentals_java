public class Part3 {
    public static void main(String[] args) {
        testing();
    }

    public static boolean twoOccurences(String stringa, String stringb){
        int count = 0;
        int index = 0;

        while(index != -1){
            index = stringb.indexOf(stringa, index+stringa.length());
            if(index == -1){
                break;
            }
            count++;
        }

        System.out.println("Count: " + count);
        if (count >= 2){
            return true;
        }
        return false;
    }


    public static String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if(index == -1){
            return stringb;
        }
        else{
            return stringb.substring(index+stringa.length());
        }
    }
    public static void testing(){
        System.out.println(twoOccurences("by", "A story by Abby Long"));
        System.out.println(twoOccurences("a", "banana"));
        System.out.println(twoOccurences("atg", "ctgtatgta"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
