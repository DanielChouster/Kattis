import java.util.Scanner;

public class ProblemA {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < N; i++)
            System.out.println(scan.nextLine().length());
    }
}
