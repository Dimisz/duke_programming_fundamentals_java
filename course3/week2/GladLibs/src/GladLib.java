import edu.duke.*;
import java.util.*;

public class GladLib {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> labelsUsed;
//	private ArrayList<String> adjectiveList;
//	private ArrayList<String> nounList;
//	private ArrayList<String> colorList;
//	private ArrayList<String> countryList;
//	private ArrayList<String> nameList;
//	private ArrayList<String> animalList;
//	private ArrayList<String> timeList;
//	private ArrayList<String> verbList;
//	private ArrayList<String> fruitList;
//	private ArrayList<String> usedWords;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "/Users/mbpro/Desktop/GladLib/datalong";
	
	public GladLib(){
		labelsUsed = new ArrayList<String>();
		myMap = new HashMap<String, ArrayList<String>>();
		//initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		labelsUsed = new ArrayList<String>();
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
//		adjectiveList= readIt(source+"/adjective.txt");
//		nounList = readIt(source+"/noun.txt");
//		colorList = readIt(source+"/color.txt");
//		countryList = readIt(source+"/country.txt");
//		nameList = readIt(source+"/name.txt");
//		animalList = readIt(source+"/animal.txt");
//		timeList = readIt(source+"/timeframe.txt");
//		verbList = readIt(source+"/verb.txt");
//		fruitList = readIt(source + "/fruit.txt");
//		usedWords = new ArrayList<String>();

		String[] labels = {"adjective", "noun", "color", "country",
							"name", "animal", "timeframe", "verb", "fruit"};
		for(String s : labels){
			ArrayList<String> list = readIt(source + "/" + s + ".txt");
			myMap.put(s, list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if(!labelsUsed.contains(label)){
			labelsUsed.add(label);
		}

		if(label.equals("number")){
			return "" + myRandom.nextInt(50) + 5;
		}
		return randomFrom(myMap.get(label));


//		if (label.equals("country")) {
//			return randomFrom(countryList);
//		}
//		if (label.equals("color")){
//			return randomFrom(colorList);
//		}
//		if (label.equals("noun")){
//			return randomFrom(nounList);
//		}
//		if (label.equals("name")){
//			return randomFrom(nameList);
//		}
//		if (label.equals("adjective")){
//			return randomFrom(adjectiveList);
//		}
//		if (label.equals("animal")){
//			return randomFrom(animalList);
//		}
//		if (label.equals("timeframe")){
//			return randomFrom(timeList);
//		}
//		if (label.equals("number")){
//			return ""+myRandom.nextInt(50)+5;
//		}
//		if (label.equals("verb")){
//			return randomFrom(verbList);
//		}
//		if (label.equals("fruit")){
//			return randomFrom(fruitList);
//		}
//		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
//		int indexOfSub = usedWords.indexOf(sub);
//		//System.out.println("Index of " + sub + " : " + indexOfSub);
//		while(indexOfSub != -1){
//			sub = getSubstitute(w.substring(first+1,last));
//			indexOfSub = usedWords.indexOf(sub);
//		}
//		usedWords.add(sub);
		return prefix+sub+suffix;
	}

	public int totalWordsInMap(){
		int total = 0;
		for (HashMap.Entry<String, ArrayList<String>> set :
				myMap.entrySet()) {
			total += set.getValue().size();
		}
		return total;
	}
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	public int totalWordsConsidered(){
		int total = 0;
		for(String label : labelsUsed){
			total += myMap.get(label).size();
		}
		return total;
	}
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("/Users/mbpro/Desktop/GladLib/datalong/madtemplate2.txt");
		printOut(story, 60);
	}

	public static void main(String[] args) {
		GladLib gl = new GladLib();
	}

}
