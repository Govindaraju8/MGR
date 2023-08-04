package CalInterest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Utility {
	private static List<Account> accounts = new ArrayList<>();
	private static List<Transaction> transactions = new ArrayList<>();

	private static final double RATE_OF_INTEREST = 4.5;
	private static final double NUMBER_OF_MONTHS = 1.0 / 12;

	private static Map<Integer, Integer> accountIndexMap = new HashMap<>();

	private static LocalDate currentDate;

	static {
		String accountsFile = "D:\\BhaskarEclipse\\CalInterestForAccounts\\src\\CalInterest\\accounts.txt";
		String line;
		String csvSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(accountsFile))) {
			int idx = 0;
			while ((line = br.readLine()) != null) {
				String[] accountData = line.split(csvSplitBy);
				int accountNumber = Integer.parseInt(accountData[0]);
				String accountType = accountData[1];
				String openingDate = accountData[2];
				double balance = Double.parseDouble(accountData[3]);

				Account account = new Account(accountNumber, accountType, openingDate, balance);
				accounts.add(account);

				accountIndexMap.put(accountNumber, idx);
				idx++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String txnFile = "D:\\BhaskarEclipse\\CalInterestForAccounts\\src\\CalInterest\\transactions.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(txnFile))) {
			while ((line = br.readLine()) != null) {
				String[] transactionData = line.split(csvSplitBy);

				int transactionId = Integer.parseInt(transactionData[0]);
				int accountNumber = Integer.parseInt(transactionData[1]);
				String transactionDate = transactionData[2];
				char transactionType = transactionData[3].charAt(0);
				int transactionAmount = Integer.parseInt(transactionData[4]);

				Transaction transaction = new Transaction(transactionId, accountNumber, transactionDate, transactionType, transactionAmount);
				transactions.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Account> getAllAccounts() {
		return accounts;
	}

	public static List<Transaction> getAllTransactions(int accountNumber) {
		return transactions.stream()
				.filter(transaction -> transaction.getAccountNumber() == accountNumber)
				.collect(Collectors.toList());
	}

	public static void setBalanceHistory(int accountNumber, LocalDate dateToCalculateFrom) {
		ArrayList<Record> balanceRecords = new ArrayList<>();
		Account account = accounts.get(accountIndexMap.get(accountNumber));
		double openingBalance = account.getBalance();
		double currentBalance = openingBalance;
		LocalDate openingDate = account.getOpeningDate();
		currentDate = openingDate;

		balanceRecords.add(new Record(openingDate, openingBalance));

		while (currentDate.getMonthValue() <= dateToCalculateFrom.getMonthValue()) {
			List<Transaction> monthTransactions = getAllTransactions(accountNumber).stream()
					.filter(transaction -> transaction.getTransactionDate().getMonth() == currentDate.getMonth())
					.collect(Collectors.toList());

			if (monthTransactions.isEmpty()) {
				balanceRecords.add(new Record(LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 11),
						currentBalance));
			} else {
				for (Transaction transaction : monthTransactions) {
					if (transaction.getTransactionType() == 'D') {
						currentBalance += transaction.getTransactionAmount();
						account.setBalance(currentBalance);
					} else {
						currentBalance -= transaction.getTransactionAmount();
						account.setBalance(currentBalance);
					}
					balanceRecords.add(new Record(transaction.getTransactionDate(), currentBalance));
				}
			}
			currentDate = currentDate.plusMonths(1);
		}

		account.setBalanceRecords(balanceRecords);
	}

	public static double calculateInterest(int accountNumber, LocalDate dateToCalculateFrom) {
		Account account = accounts.get(accountIndexMap.get(accountNumber));
		setBalanceHistory(accountNumber, dateToCalculateFrom);
		List<Record> balanceRecords = account.getBalanceRecords();

		double totalInterest = 0;
		double minBalanceOfMonth = Double.MAX_VALUE;

		while (currentDate.isAfter(dateToCalculateFrom)) {
			currentDate = currentDate.minusMonths(1);
			List<Record> monthBalanceRecords = balanceRecords.stream()
					.filter(record -> record.getDate().getMonth() == currentDate.getMonth())
					.collect(Collectors.toList());

			for (Record record : monthBalanceRecords) {
				if (record.getBalance() < minBalanceOfMonth) {
					minBalanceOfMonth = record.getBalance();
				}
			}

			totalInterest += (minBalanceOfMonth * NUMBER_OF_MONTHS * RATE_OF_INTEREST) / 100;
		}

		return totalInterest;
	}
}
