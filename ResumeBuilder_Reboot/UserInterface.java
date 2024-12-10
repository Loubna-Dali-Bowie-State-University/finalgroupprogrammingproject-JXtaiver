import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class UserInterface {
    static Scanner scnr = new Scanner(System.in);
    static String UserInput = "";

    public static void createResume(Scanner scnr) {
        System.out.println("Creating Resume...");
        System.out.println("Enter Your Name: ");
        scnr.nextLine(); // Consume the leftover newline
        String name = scnr.nextLine();
        
        System.out.println("\nEnter email: ");
        String email = scnr.nextLine();
        System.out.println("\nEnter your age: ");
        int age = scnr.nextInt();
        scnr.nextLine(); // Consume the leftover newline
        System.out.println("Enter past experiences: ");
        String experience = scnr.nextLine();
        
        System.out.println("Enter any skills: "); 
        String skills = scnr.nextLine();

        // Saving details to a file
        try (FileWriter writer = new FileWriter(name + "_resume.txt")) {
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Experience: " + experience + "\n");
            writer.write("Skills: " + skills + "\n");
            System.out.println("Resume created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the resume.");
            e.printStackTrace();
        }
    }

    public static void openExistingResume(Scanner scnr) {
        System.out.println("Opening resume...");
        System.out.println("Enter the name on the resume: ");
        String name = scnr.next();
        File file = new File(name + "_resume.txt");

        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    System.out.println(fileScanner.nextLine());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the resume.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Resume not found.");
        }
    }
    
    public void Menu() {
        System.out.println("Welcome to the resume builder program!");
        System.out.println("What would you like to do?");
        System.out.println("""
                1: [C]reate new resume
                2: [O]pen existing resume
                3: E[x]it the program
                """);

        Set<String> validInputs = Set.of("C", "O", "x");

        do { 
            UserInput = scnr.next();
            if (!validInputs.contains(UserInput)) {
                System.out.println("Enter a valid input!");
            }
        } while (!validInputs.contains(UserInput));

        switch (UserInput) {
            case "C" -> {
                System.out.println("Creating new resume...");
                createResume(scnr); // Pass the scanner
            }
            case "O" -> {
                System.out.println("Opening existing resume...");
                openExistingResume(scnr); // Pass the scanner
            }
            case "x" -> {
                System.out.println("Exiting the program...");
                System.exit(0);
            }
            default -> System.out.println("Invalid option!");
        }
        UserInterface ui = new UserInterface();
        ui.Menu();
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.Menu();
    }
}