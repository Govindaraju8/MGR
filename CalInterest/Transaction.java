package CalInterest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Transaction {
    private int transactionId;
    private int accountNumber;
    private LocalDate transactionDate;
    private char transactionType;
    private int transactionAmount;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Transaction(int transactionId, int accountNumber, String transactionDate, char transactionType, int transactionAmount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionDate = LocalDate.parse(transactionDate, formatter);
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return String.valueOf(transactionId);
    }
}
