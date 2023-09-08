import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Transaction> transactionHistory = new ArrayList<>();
    private static double balance = 1000.0;

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM Interface!");
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            System.out.println("Authentication successful!");
            showMainMenu();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    private static boolean authenticateUser(String userId, String pin) {

        return userId.equals("12345") && pin.equals("1234");
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nATM Main Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice (1/2/3/4/5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM Interface. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful.");
        }
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Deposit failed.");
        } else {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful.");
        }
    }

    private static void transfer() {
        System.out.print("Enter the recipient's account number: ");
        String recipientAccount = scanner.nextLine();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid transfer amount. Transfer failed.");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("Transfer to " + recipientAccount, amount));
            System.out.println("Transfer successful.");
        }
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}
