package database;

import budget.Budget;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDb {
    static Scanner sc = new Scanner(System.in);
    static MongoCollection<Document> mcol;
    public static void mongoService() {
        try {
            MongoClient mc = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase md = mc.getDatabase("JavaUser");
            mcol = md.getCollection("betUsers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void signUp () {
        mongoService();
        System.out.print("Sign Up Now!\nEnter Username to Sign Up: ");
        String userName = sc.nextLine();
        System.out.print("Make A Password: ");
        String password = sc.nextLine();
        Document userDoc = new Document();
        userDoc.append("username", userName);
        userDoc.append("password", password);
        userDoc.append("budget", Budget.fee);
        mcol.insertOne(userDoc);
        System.out.println("Sign Up Successfully!");
        System.out.println("Welcome " + userName + "!");

    }
    public static void login () {
        while (true) {
            mongoService();
            System.out.print("Enter Username to Login : ");
            String userName = sc.nextLine();
            System.out.print("Enter Your Password: ");
            String password = sc.nextLine();
            Object o = mcol.find(and(eq("username", userName), eq("password", password))).first();
            if (o != null) {
                System.out.println("Hello " + userName + "! ");
                return;
            } else {
                System.err.println("Wrong Username Or Password!");
            }
        }
    }
    public static void saveBudget () {
        Object o =mcol.find(eq("budget", Budget.fee));
        if (o != null) {
            System.out.println("Your current Budget is : " + Budget.fee);
        } else {
            System.err.println("Purpose not found");
        }
    }
}
