import edu.duke.FileResource;

public class WordLengths {
    public static void main(String[] args) {
        testCountWordLengths();
    }
    public static void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            //System.out.println(word + ":\t" + word.length());
            int lengthCount = 0;
            for(int j = 0; j < word.length(); j++){
                if(Character.isLetter(word.charAt(j)) || word.charAt(j) == '\''){
                    lengthCount++;
                }
            }
            //System.out.println(word + ":\t" + lengthCount);
            counts[lengthCount] += 1;
        }

        for(int i = 0; i < counts.length; i++){
            System.out.println(i + ": " + counts[i]);
        }
    }

    public static int indexOfMax(int[] arr){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++){
            if(arr[i] > maxValue){
                maxValue = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        System.out.println("Max index is: " + indexOfMax(counts));
    }
}
