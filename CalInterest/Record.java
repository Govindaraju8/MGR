package CalInterest;

import java.time.LocalDate;

class Record {
	private LocalDate date;
	private double balance;

	public Record(LocalDate date, double balance) {
		this.date = date;
		this.balance = balance;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public double getBalance() {
		return this.balance;
	}

	@Override
	public String toString() {
		return this.date + " " + this.balance;
	}
}