package KIT.std;
import java.util.*;

public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public void recordTransaction(String accountNumber, String type, double amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, type, amount);
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        List<Transaction> history = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountNumber().equals(accountNumber)) {
                history.add(t);
            }
        }
        return history;
    }
}
