package roulette;

import budget.Budget;
import database.MongoDb;

import java.util.Random;
import java.util.Scanner;

public class Range {
    private static final Random rand=new Random();
    private static int number1;
    private static int number2;
    private static int random;
    public static void play() {
        System.out.println("Choose a range of ten between 0 and 99 !");
        Scanner scanner = new Scanner(System.in);
        System.out.print("The range starts from : ");
        number1 = scanner.nextInt();
        number2 =number1+10;
        System.out.println("The range ends with : "+number2);
        random = rand.nextInt(100);
        System.out.println("The number obtained is: " + random);
        range();
    }
    private static void range(){
        boolean btw=(random>=number1 && random<=number2);
        if (btw){
            earn();
        }else {
            loss();
        }
    }
    private static void earn(){
        Budget.pay*=1.5f;
        Budget.fee= Budget.res+ Budget.pay;
        System.out.println("Excellent!\nThe money you put in the bet became : " + Budget.pay);
        MongoDb.saveBudget();
    }
    private static void loss(){
        Budget.pay=0;
        Budget.fee= Budget.res+ Budget.pay;
        System.out.println("Unfortunately you Lost the Bet!" +
                "\nThe money you put in the bet became : " + Budget.pay);
        MongoDb.saveBudget();
    }
}
