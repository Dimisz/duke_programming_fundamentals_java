public class Part1 {
    public static void main(String[] args) {
        testSimpleGene();
    }


    public static String findSimpleGene(String dna){
        String gene = "";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex+3);

        if(startIndex == -1 || stopIndex == -1){
            gene = "Not found";
        }
        else {
            gene = dna.substring(startIndex, stopIndex+3);
        }
        return gene;
    }

    public static void testSimpleGene(){
        // prints "ATGGAAGAAFDDTAA"
        System.out.print("The gene: ");
        System.out.println(findSimpleGene("ATGGAAGAAFDDTAADD"));
        //prints "Not found"
        System.out.print("The gene: ");
        System.out.println(findSimpleGene("TGGAAGAAFDDTAADD"));
        //prints "Not found"
        System.out.print("The gene: ");
        System.out.println(findSimpleGene("ATGGAAGAAFDDTADD"));
        //prints "ATGDDTAA"
        System.out.print("The gene: ");
        System.out.println(findSimpleGene("ATGDDTAAD"));

    }
}
