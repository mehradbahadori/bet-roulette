package roulette;

import budget.Budget;
import database.MongoDb;

import java.util.Random;
import java.util.Scanner;

public class List {
    private static final Random rand=new Random();
    private static final Scanner sc=new Scanner(System.in);
    private static boolean btw;
    private static boolean right;
    private static int number1;
    private static int number2;
    private static int number3;
    private static int number4;
    private static int number5;
    private static int number6;
    private static int random1;
    private static int random2;
    private static int random3;
    public static void play(){
        System.out.println("What Kind of Roulettes do you Need in Your roulette.List? " +
                "Select one of these:" +
                "\n1.(casual-casual-casual),2.(casual-casual-range)" +
                ",3.(range-range-casual),4.(range-range-range)");
        int input=sc.nextInt();
        switch (input) {
            case 1 : casual();
                break;
            case 2 : casCasRng();
                break;
            case 3 : rngRngCas();
                break;
            case 4 : range();
                break;
        }
    }
    private static void casual() {
        System.out.println("Choose three numbers Between 0 and 99!");
        System.out.print("The first number is : ");
        number1 = sc.nextInt();
        random1 = rand.nextInt(100);
        System.out.print("The Second number is : ");
        number2 = sc.nextInt();
        random2 = rand.nextInt(100);
        System.out.print("The Third number is : ");
        number3 = sc.nextInt();
        random3 = rand.nextInt(100);
        obtained();
        right = (number1 == random1 && number2 == random2 && number3 == random3);
        if (right) {
            earnCas();
        } else {
            loss();
        }
    }
    private static void casCasRng(){
        System.out.println("Choose two Numbers and a roulette.Range of ten Between 0 and 99!");
        System.out.print("The First number is : ");
        number1= sc.nextInt();
        random1=rand.nextInt(100);
        System.out.print("The Second number is : ");
        number2= sc.nextInt();
        random2=rand.nextInt(100);
        System.out.print("The range starts from : ");
        number3= sc.nextInt();
        number4=number3+10;
        System.out.println("The range ends with : "+number4);
        random3=rand.nextInt(100);
        obtained();
        right =(number1==random1 && number2==random2 && (random3>=number3 && random3<=number4));
        if (right){
            earnCasCasRng();
        }else {
            loss();
        }
    }
    private static void rngRngCas(){
        System.out.println("Choose two ranges of ten and a number between 0 and 99!");
        System.out.print("The First range starts from : ");
        number1=sc.nextInt();
        number2=number1+10;
        System.out.println("The First range ends with : "+number2);
        random1 = rand.nextInt(100);
        System.out.print("The Second range starts from : ");
        number3=sc.nextInt();
        number4=number3+10;
        System.out.println("The Second range ends with : "+number4);
        random2 = rand.nextInt(100);
        System.out.print("The number is : ");
        number5=sc.nextInt();
        random3 = rand.nextInt(100);
        obtained();
        rngRngCasRes();
    }
    private static void range(){
        System.out.println("Choose three ranges of ten between 0 and 99!");
        System.out.print("The First range starts from : ");
        number1=sc.nextInt();
        number2=number1+10;
        System.out.println("The First range ends with : "+number2);
        random1 = rand.nextInt(100);
        System.out.print("The Second range starts from : ");
        number3=sc.nextInt();
        number4=number3+10;
        System.out.println("The Second range ends with : "+number4);
        random2 = rand.nextInt(100);
        System.out.print("The Third range starts from : ");
        number5=sc.nextInt();
        number6=number5+10;
        System.out.println("The Third range ends with : "+number6);
        random3 = rand.nextInt(100);
        obtained();
        rngRes();
    }
    private static void rngRngCasRes(){
        btw=(random1>=number1 &&random1<=number2);
        if (btw){
            btw=(random2>=number3 && random2<=number4);
            if (btw){
                right=(random3==number5);
                if (right){
                    earnRngRngCas();
                }else {
                    loss();
                }
            }else {
                loss();
            }
        }else {
            loss();
        }
    }
    private static void rngRes(){
        btw=(random1>=number1 && random1<=number2);
        if (btw){
            btw=(random2>=number3 && random2<=number4);
            if (btw){
                btw=(random3>=number5 && random3<=number6);
                if (btw){
                    earnRng();
                }else {
                    loss();
                }
            }else {
                loss();
            }
        }else {
            loss();
        }
    }
    private static void obtained(){
        System.out.println("The First number obtained is: " + random1);
        System.out.println("The Second number obtained is: " + random2);
        System.out.println("The Third number obtained is: " + random3);
    }
    private static void earnCas(){
        Budget.pay*=8f;
        earn();
    }
    private static void earnCasCasRng(){
        Budget.pay*=6f;
        earn();
    }
    private static void earnRngRngCas(){
        Budget.pay*=4.5f;
        earn();
    }
    private static void earnRng(){
        Budget.pay*=3.375f;
        earn();
    }
    private static void earn(){
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
