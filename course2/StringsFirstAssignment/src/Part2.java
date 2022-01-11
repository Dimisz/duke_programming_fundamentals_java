public class Part2 {
    public static void main(String[] args) {
        System.out.println(findSimpleGene("ATGGGTTAAGTC", 0, 6));
    }
    public static String findSimpleGene(String dna, int start, int stop){
        String gene = "";

        int startIndex = start;
        int stopIndex = stop;


        gene = dna.substring(startIndex, stopIndex+3);

        return gene;
    }
}
