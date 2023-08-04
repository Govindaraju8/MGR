package CalInterest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Account {
	private int accountNumber;
	private String accountType;
	private LocalDate openingDate;
	private double balance;
	private ArrayList<Record> balanceHistory;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public Account(int accountNumber, String accountType, String openingDate, double balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.openingDate = LocalDate.parse(openingDate, formatter);
		this.balance = balance;
	}

	public LocalDate getOpeningDate() {
		return this.openingDate;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double amt) {
		this.balance = amt;
	}

	public String toString() {
		return String.valueOf(this.accountNumber);
	}

	public void setBalanceRecords(ArrayList<Record> balRecords) {
		this.balanceHistory = balRecords;
	}

	public ArrayList<Record> getBalanceRecords() {
		return this.balanceHistory;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}
}