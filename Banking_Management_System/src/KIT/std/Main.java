package KIT.std;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static BankService bankService;
    private static TransactionService transactionService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        transactionService = new TransactionService();
        bankService = new BankService(transactionService);

        boolean running = true;

        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAccountDetails();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    manageAccountSettings();
                    break;
                case 8:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Banking System Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View Account Details");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Withdraw Funds");
        System.out.println("5. Transfer Funds");
        System.out.println("6. View Transaction History");
        System.out.println("7. Manage Account Settings");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        bankService.createAccount(accountNumber, name, email);
    }

    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = bankService.getAccount(accountNumber);
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bankService.deposit(accountNumber, amount);
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bankService.withdraw(accountNumber, amount);
    }

    private static void transferFunds() {
        System.out.print("Enter sender account number: ");
        String fromAccount = scanner.nextLine();
        System.out.print("Enter receiver account number: ");
        String toAccount = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bankService.transferFunds(fromAccount, toAccount, amount);
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    private static void manageAccountSettings() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        bankService.updateAccount(accountNumber, newEmail);
    }
}
