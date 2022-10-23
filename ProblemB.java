import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class ProblemB {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        DecimalFormat df = new DecimalFormat("#.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        int mainIndex = 0;

        int T = scan.nextInt();
        for (int k = 0; k < T; k++) {

            int R = scan.nextInt(); //Number of ingredients
            int P = scan.nextInt(); //Number of portions that the recipe is written for.
            int D = scan.nextInt(); //Number of desired portions
            String[] name = new String[R];
            double[] weight = new double[R];
            double[] percentage = new double[R];
            for (int i = 0; i < R; i++) {
                name[i] = scan.next();
                weight[i] = scan.nextDouble();
                percentage[i] = scan.nextDouble();
                if (percentage[i] == 100.0) {
                    mainIndex = i;
                    weight[i] *= (double) D / (double) P;
                }
            }

            for (int i = 0; i < R; i++) {
                if (i == mainIndex)
                    continue;
                weight[i] = weight[mainIndex] * percentage[i] / 100;
            }
            System.out.println("Recipe # " + (k + 1));

            for (int j = 0; j < R; j++) {
                System.out.println(name[j] + " " + df.format(weight[j]));
            }
            System.out.println("----------------------------------------");
        }
    }
}