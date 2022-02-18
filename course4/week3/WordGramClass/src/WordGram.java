
public class WordGram {
    private String[] myWords;
    private int myHash;
    private String myWordsString;

    public int getMyHash(){
        return myHash;
    }

    public String getMyWordsString(){
        return myWordsString;
    }

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = hashCodeForGram();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(int i = 0; i < myWords.length; i++){
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int i = 0; i < myWords.length; i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        int lastIndex = out.length() - 1;
        for(int i = 0; i < lastIndex; i++){
            out.myWords[i] = out.myWords[i+1];
        }
        out.myWords[lastIndex] = word;
        return out;
    }

    private int hashCodeForGram(){
        StringBuilder sb = new StringBuilder();
        for(String word : myWords){
            sb.append(word);
            sb.append(" ");
        }
        String gram = sb.toString().trim();
        myWordsString = gram;
        return gram.hashCode();
    }

}