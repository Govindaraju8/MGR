import java.util.ArrayList;
import java.util.List;

class Account {

	// data members
	private int ano;
	private String title;
	private double balance;

	// constructor
	public Account() {
		this(1000, "", 0);
	}

	// constructor
	public Account(int a, String t, double b) {
		ano = a;
		title = t;
		balance = b;
	}

	public int getAno() {
		return ano;
	}

	public double getBalance() {
		return balance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String t) {
		title = t;
	}

	// method to deposit
	public void deposit(double amt) {
		balance += amt;
		System.out.println("Balance after deposit is " + balance);
	}

	// method definition to withdraw
	public void withdraw(double amt) {
		if (balance >= amt)
			balance -= amt;
	}

	public void calInterest() {
		System.out.println("interest in account");
	}

}

class SavingsAccount extends Account {

	public void withdraw(double amt) {

		System.out.println("Savngs class implementatn of wthdraw");
	}

	public void transferFunds() {
		System.out.println("Transfer of funds");

	}

	public void calInterest() {
		System.out.println("Interest in Savings");
	}
}

class CurrentAccount extends Account {

	public void withdraw(double amt) {

		System.out.println("Current Account implementatn of wthdraw");
	}

	public void calODInt() {
		System.out.println("OD Calculaton");

	}

	public void calInterest() {
		System.out.println("Interest in Current");
	}
}

class RecurrentAccount extends Account {

	public void withdraw(double amt) {

		System.out.println("Recurrent class implementatn of wthdraw");
	}

	public void calMatAmount() {
		System.out.println("Mat Amount calculatn");

	}

	public void calInterest() {
		System.out.println("Interest in Recurrent");
	}
}

public class InterestCalculator {

	public void forList(List<? extends Account> li) {
		for (Account acc : li) {
			acc.calInterest();
			// if (acc instanceof RecurrentAccount) {
			// RecurrentAccount r = (RecurrentAccount) acc;
			// r.calMatAmount();
			// }
		}
	}

	public static void main(String args[]) {
		SavingsAccount sa1 = new SavingsAccount();
		SavingsAccount sa2 = new SavingsAccount();
		SavingsAccount sa3 = new SavingsAccount();

		CurrentAccount ca1 = new CurrentAccount();
		CurrentAccount ca2 = new CurrentAccount();
		CurrentAccount ca3 = new CurrentAccount();

		RecurrentAccount ra1 = new RecurrentAccount();
		RecurrentAccount ra2 = new RecurrentAccount();
		RecurrentAccount ra3 = new RecurrentAccount();

		List<SavingsAccount> sa = new ArrayList<>();
		List<CurrentAccount> ca = new ArrayList<>();
		List<RecurrentAccount> ra = new ArrayList<>();

		sa.add(sa1);
		sa.add(sa2);
		sa.add(sa3);
		ca.add(ca1);
		ca.add(ca2);
		ca.add(ca3);
		ra.add(ra1);
		ra.add(ra2);
		ra.add(ra3);

		InterestCalculator ic = new InterestCalculator();
		ic.forList(sa);
		ic.forList(ca);
		ic.forList(ra);

	}

}
