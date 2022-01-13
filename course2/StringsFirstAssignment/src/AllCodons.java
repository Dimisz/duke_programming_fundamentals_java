public class AllCodons {


    public static void main(String[] args) {
        testFindStop();
        testFindGene();
    }


    public static int findStopCodon(String dnaStr,
                                    int startIndex,
                                    String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
            }
        }
        return -1;
    }

    public static String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
//        int temp = Math.min(taaIndex, tagIndex);
//        int minIndex = Math.min(temp, tgaIndex);
        //int minIndex = Math.min(tgaIndex, Math.min(taaIndex, tagIndex));
        int minIndex = 0;
        if (taaIndex == -1 ||
                (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }

        if(minIndex == -1 ||
                (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }

        if(minIndex == dna.length()){
            return "";
        }
        else{
            return dna.substring(startIndex, minIndex+3);
        }
    }

    public static void testFindStop(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("Error on 9");

        dex = findStopCodon(dna, 12, "TAA");
        //System.out.println(dex);
        if(dex != 21) System.out.println("Error on 21");

        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) System.out.println("Error on 26");

        dex = findStopCodon(dna, 0, "TAG");
        if(dex != 26) System.out.println("Error on 26 TAG");

        System.out.println("Tests finished");
    }

    public static void testFindGene(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna);
        if(! gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
}
