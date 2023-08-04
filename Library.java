import java.util.HashMap;
import java.util.Map;

class Book {
	String Title;
	String Author;
	String Publisher;

	public Book(String T, String A, String P) {
		this.Title = T;
		this.Author = A;
		this.Publisher = P;

	}

}

public class Library {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book b1 = new Book("Cse1", "CSEBOOKS", "CSEHEAD");
		Book b2 = new Book("Cse2", "CSEBOOKS", "CSEHEAD");
		Book b3 = new Book("Cse3", "CSEBOOKS", "CSEHEAD");
		Book b4 = new Book("Ece1", "ECEBOOKS", "ECEHEAD");
		Book b5 = new Book("Ece2", "ECEBOOKS", "ECEHEAD");
		Book b6 = new Book("Ece3", "ECEBOOKS", "ECEHEAD");
		Book b7 = new Book("Eee1", "EEEBOOKS", "EEEHEAD");
		Book b8 = new Book("Eee2", "EEEBOOKS", "EEEHEAD");
		Book b9 = new Book("Eee3", "EEEBOOKS", "EEEHEAD");
		Map<String, Book> Csebooks = new HashMap<>();
		Csebooks.put("ISBNCSE1", b1);
		Csebooks.put("ISBNCSE2", b2);
		Csebooks.put("ISBNCSE3", b3);
		Map<String, Book> Ecebooks = new HashMap<>();
		Ecebooks.put("ISBNECE1", b4);
		Ecebooks.put("ISBNECE2", b5);
		Ecebooks.put("ISBNECE3", b6);

		Map<String, Book> Eeebooks = new HashMap<>();
		Eeebooks.put("ISBNEEE1", b7);
		Eeebooks.put("ISBNEEE2", b8);
		Eeebooks.put("ISBNEEE3", b9);

		Map<String, HashMap<String, Book>> Lib = new HashMap<>();
		Lib.put("CSE", (HashMap<String, Book>) Csebooks);
		Lib.put("ECE", (HashMap<String, Book>) Ecebooks);
		Lib.put("EEE", (HashMap<String, Book>) Eeebooks);
		for (String category : Lib.keySet()) {
			System.out.println("Category: " + category);
			System.out.println();

			System.out.println("------------------------------------------------------");
			System.out.printf("%-12s | %-10s | %-10s | %-20s%n", "ISBN", "Title", "Author", "Publisher");
			System.out.println("------------------------------------------------------");

			HashMap<String, Book> booksInCategory = Lib.get(category);
			for (String isbn : booksInCategory.keySet()) {
				Book book = booksInCategory.get(isbn);
				System.out.printf("%-12s | %-10s | %-10s | %-20s%n", isbn, book.Title, book.Author, book.Publisher);
			}
			System.out.println();
		}

	}

}
