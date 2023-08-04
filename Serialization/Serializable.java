// Serialization and Deserialzation using using objectinputoutputstream and fileInputoutputstream
import java.io.*;
import java.io.Serializable;
class Account implements Serializable{
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


}

public class Serialzation{
 public static void main(String args[]){    
  try{    
  //Serialization using objectoutputStream and fileoutputStream
  Account a1 =new Account(123,"ravi",10000);    
  //Creating stream and writing the object    
  FileOutputStream fout=new FileOutputStream("demo.txt");    
  ObjectOutputStream out=new ObjectOutputStream(fout);    
  out.writeObject(a1);    
  out.flush();    
  //closing the stream    
  out.close();   
  

// Deserialization using objectInputStream and fileInputStream
  ObjectInputStream in=new ObjectInputStream(new FileInputStream("demo.txt"));  
  Account aa=(Account)in.readObject();  
  //printing the data of the serialized object  
  aa.withdraw(1000);
  System.out.println(aa.getAno()+" "+aa.getTitle()+" "+aa.getBalance());  
  //closing the stream  
  in.close();  
  System.out.println("success");    
  }catch(Exception e){System.out.println(e);}    
 }    
}    
