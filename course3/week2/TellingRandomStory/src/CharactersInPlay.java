import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> playCharacters;
    private ArrayList<Integer> counts;

    public CharactersInPlay(){
        playCharacters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person){
        int index = playCharacters.indexOf(person);
        if(index == -1){
            playCharacters.add(person);
            counts.add(1);
        }
        else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }

    private void findAllCharacters(){
        playCharacters.clear();
        counts.clear();
        FileResource resource = new FileResource();

        for(String line : resource.lines()){
            int indexOfDot = line.indexOf('.');
            if(indexOfDot != -1){
                String person = line.substring(0, indexOfDot);
                update(person);
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < counts.size(); i++){
            int num = counts.get(i);
            if(num >= num1 && num <= num2){
                System.out.print(playCharacters.get(i));
                System.out.println("\toccurred: " + counts.get(i));
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i = 0; i < playCharacters.size(); i++){
            if(counts.get(i) > 1) {
                System.out.print("Character: " + playCharacters.get(i) + "\t");
                System.out.println("Occurences: " + counts.get(i));
            }
        }
    }
}
