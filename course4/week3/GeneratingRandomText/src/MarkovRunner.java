
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

import java.util.ArrayList;

public class MarkovRunner {
    public void runMarkovZero() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
	//	String st = "This is a test yes a test";
		MarkovZero markov = new MarkovZero();
		markov.setRandom(88);
		markov.setTraining(st);
//		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
//		}
	}

	public void runMarkovOne() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
//		String st = "this is a test yes this is a test.";
		MarkovOne markov1 = new MarkovOne();
		markov1.setRandom(273);
		markov1.setTraining(st);
//		for(int k=0; k < 3; k++){
			String text = markov1.getRandomText(500);
			printOut(text);
//		}
	}

	public void runMarkovFour() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
//		String st = "this is a test yes this is a test.";
		MarkovFour markovFour = new MarkovFour();

		markovFour.setRandom(371);
		markovFour.setTraining(st);
//		for(int k=0; k < 3; k++){
			String text = markovFour.getRandomText(500);
			printOut(text);
//		}
	}

	public void runMarkovModel() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markovModel = new MarkovModel(8);

		markovModel.setRandom(365);
		markovModel.setTraining(st);
		String text = markovModel.getRandomText(500);
		printOut(text);
	}
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
//		mr.runMarkovZero();
//		mr.runMarkovOne();
//		mr.runMarkovFour();
		mr.runMarkovModel();
	}
}
