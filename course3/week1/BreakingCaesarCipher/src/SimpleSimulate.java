import java.util.Random;

public class SimpleSimulate {
    public static void main(String[] args) {
        simpleSimulate(10000);
    }
    public static void simpleSimulate(int rolls){
        Random rand = new Random();
        int [] counts = new int [13];

        for(int i = 0; i < rolls; i++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1+d2] += 1;
        }

        for(int j = 2; j <= 12; j++){
            System.out.println(j + "'s:\t" + counts[j] + "\t" + 100*counts[j]/rolls + "%");
        }
    }

}
