import roulette.Casual;
import roulette.List;
import roulette.Range;

import budget.Budget;
import database.MongoDb;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String input;
    public static void main(String[] args) {
        System.out.println("Hello!\nWelcome to the MehradBet.");
        home();
        roulette();
    }
    private static void home() {
        while (true) {
            System.out.println("Are you Registered?\ty(Yes) / n(No)");
            input = scanner.nextLine();
            switch (input) {
                case "n" : MongoDb.signUp();
                    break;
                case "y" : {
                    MongoDb.login();
                    return;
                }
            }
        }
    }
    private static void roulette() {
        while (true) {
            System.out.println("Do you Want to Play Roulette?\tyes? / no?");
            input = scanner.nextLine();
            switch (input) {
                case "no" : {
                    System.out.println("home? / exit?");
                    input = scanner.nextLine();
                    if (input.equals("home")) {
                        home();
                    } else if (input.equals("exit")) {
                        return;
                    }
                }
                break;
                case "yes" : {
                    Budget.budget();
                    System.out.println("What kind of roulette do you want to play?\tcasual? / range? / list?");
                    input = scanner.nextLine();
                    switch (input) {
                        case "casual" : Casual.play();
                            break;
                        case "range" : Range.play();
                            break;
                        case "list" : List.play();
                            break;
                    }
                    if (Budget.fee==0){
                        System.err.println("\nyou lose!\tgame over!");
                        return;
                    }else if (Budget.fee==10000){
                        System.out.println("\nYou Win!");
                        return;
                    }
                }
            }
        }
    }
}
