package KIT.std;
import java.util.*;

public class BankService {
    private List<BankAccount> accounts = new ArrayList<>();
    private TransactionService transactionService;

    public BankService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public BankAccount createAccount(String accountNumber, String name, String email) {
        if (findAccount(accountNumber) != null) {
            System.out.println("Account already exists!");
            return null;
        }
        BankAccount account = new BankAccount(accountNumber, name, email, 0);
        accounts.add(account);
        System.out.println("Account created successfully.");
        return account;
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public BankAccount getAccount(String accountNumber) {
        return findAccount(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        transactionService.recordTransaction(accountNumber, "Deposit", amount);
        System.out.println("Deposit successful.");
        return true;
    }

    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }
        if (amount <= 0 || amount > account.getBalance()) {
            System.out.println("Invalid amount or insufficient balance.");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        transactionService.recordTransaction(accountNumber, "Withdrawal", amount);
        System.out.println("Withdrawal successful.");
        return true;
    }

    public boolean transferFunds(String fromAccount, String toAccount, double amount) {
        BankAccount sender = findAccount(fromAccount);
        BankAccount receiver = findAccount(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found.");
            return false;
        }
        if (amount <= 0 || amount > sender.getBalance()) {
            System.out.println("Invalid amount or insufficient funds.");
            return false;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        transactionService.recordTransaction(fromAccount, "Transfer Out", amount);
        transactionService.recordTransaction(toAccount, "Transfer In", amount);
        System.out.println("Transfer successful.");
        return true;
    }

    public boolean updateAccount(String accountNumber, String newEmail) {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }
        account.setEmail(newEmail);
        System.out.println("Account updated successfully.");
        return true;
    }
}
