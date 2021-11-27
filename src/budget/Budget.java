package budget;

import java.util.Scanner;

public class Budget {
    public static float fee = 200;
    public static float pay;
    public static float res;
    public static void budget() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Your Budget is : " + Budget.fee);
            System.out.println("How Much Money Do You Want to Bet?");
            pay = sc.nextFloat();
            res = Budget.fee - pay;
            if (pay <= fee) return;
            else {
                System.err.println("Your budget is not Enough!" +
                        "\tPlease decide according to your financial situation...");
            }
        }
    }
}

