package roulette;

import budget.Budget;
import database.MongoDb;

import java.util.Random;
import java.util.Scanner;

public class Casual {
    private static final Random rand=new Random();
    private static final Scanner sc=new Scanner(System.in);
    public static void play(){
        System.out.println("Choose a number Between 0 and 99!");
        int number=sc.nextInt();
        int random=rand.nextInt(100);
        System.out.println("The number obtained is: " +random);
        if (number==random){
            Budget.pay*=2;
            Budget.fee= Budget.res+ Budget.pay;
            System.out.println("Excellent!\nThe money you put in the bet became : " + Budget.pay);
            MongoDb.saveBudget();
        }else{
            Budget.pay=0;
            Budget.fee= Budget.res+ Budget.pay;
            System.out.println("Unfortunately you Lost the Bet!" +
                    "\nThe money you put in the bet became : " + Budget.pay);
        }
        MongoDb.saveBudget();
    }
}
