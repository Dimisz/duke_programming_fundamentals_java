import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne markovOne = new MarkovOne();
        String trainingString = "this is a test yes this is a test.";
        markovOne.setTraining(trainingString);
        ArrayList<String> trainingList = new ArrayList<String>();
        trainingList.add("t");
        trainingList.add("e");
        trainingList.add("es");
        trainingList.add(".");
        trainingList.add("t.");
        for(String word : trainingList){
            System.out.print(word + ":\t");
            System.out.println(markovOne.getFollows(word));
        }
    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining(st);
        System.out.println(markovOne.getFollows("th").size());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testGetFollows();
        tester.testGetFollowsWithFile();
    }
}
