package CalInterest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateToCalFrom = sc.nextLine();
		LocalDate dateToCal = LocalDate.parse(dateToCalFrom, formatter);
		for (Account account : Utility.getAllAccounts()) {
			System.out.println(Utility.calculateInterest(account.getAccountNumber(), dateToCal));
		}

	}

}


