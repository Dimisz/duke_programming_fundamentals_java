import edu.duke.StorageResource;

public class FindGeneWhile {
    public static void main(String[] args) {
        //testFindGeneSimple();
        testOn("AATGCGTAATTAATCG");
    }
    public static String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        int currIndex = dna.indexOf("TAA", startIndex+3);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return dna.substring(startIndex, currIndex+3);
            }
            else {
                currIndex = dna.indexOf("TAA", currIndex+1);
            }
        }
        return "";

    }

    public static void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
           //find the next gene after startIndex
           String currentGene = findGene(dna, startIndex);
           //if no gene found -> leave the loop
            if (currentGene.isEmpty()){
                break;
            }
           //print the gene
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public static StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            //find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //if no gene found -> leave the loop
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            //print the gene
            //System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }


    //TESTS
    public static void testFindGeneSimple(){
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is: " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene is: " + gene);
    }

    public static void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()){
            System.out.println(g);
        }
        //printAllGenes(dna);
    }

    public static void test(){
        testOn("ATGATCTAATTTATGGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
