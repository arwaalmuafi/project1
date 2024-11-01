import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static double lastResult = 0;
    public static ArrayList<Double> results = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = input.nextInt();

            if (choice >= 1 && choice <= 8) {
                System.out.print("Enter first number: ");
                double num1 = input.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = input.nextDouble();

                switch (choice) {
                    case 1 -> lastResult = add(num1, num2);
                    case 2 -> lastResult = subtract(num1, num2);
                    case 3 -> lastResult = multiply(num1, num2);
                    case 4 -> lastResult = divide(num1, num2);
                    case 5 -> lastResult = modulus(num1, num2);
                    case 6 -> lastResult = min(num1, num2);
                    case 7 -> lastResult = max(num1, num2);
                    case 8 -> lastResult = average(num1, num2);
                }
                results.add(lastResult);
                System.out.println("Result: " + lastResult);
            } else if (choice == 9) {
                System.out.println("Last result: " + lastResult);
            } else if (choice == 10) {
                System.out.println("All results: " + results);
            } else if (choice == 11) {
                System.out.println("Exiting the calculator. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\nCalculator Menu:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        System.out.println("6. Minimum");
        System.out.println("7. Maximum");
        System.out.println("8. Average");
        System.out.println("9. Print last result");
        System.out.println("10. Print all results");
        System.out.println("11. Exit");
        System.out.print("Choose an option: ");
    }

    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }
    public static double multiply(double a, double b) {
        return a * b;
    }
    public static double divide(double a, double b) {
        return b != 0 ? a / b : Double.NaN;
    }
    public static double modulus(double a, double b) {
        return b != 0 ? a % b : Double.NaN;
    }
    public static double min(double a, double b) {
        return Math.min(a, b);
    }
    public static double max(double a, double b) {
        return Math.max(a, b);
    }
    public static double average(double a, double b) {
        return (a + b) / 2;
    }
}
