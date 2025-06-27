package KIT.std;

public class Transaction {
	private String transactionId;
	private String accountNumber;
	private String type;
	private double amount;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Transaction(String transactionId, String accountNumber, String type, double amount) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.type = type;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", type=" + type
				+ ", amount=" + amount + "]";
	}
	
}
