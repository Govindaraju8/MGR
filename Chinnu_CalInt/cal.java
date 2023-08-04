import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
class IntCal
{

	List<Account> accList=new ArrayList<>();
	List<Transaction> transacList=new ArrayList<>();
	public static void main(String args[])
	{

		IntCal c=new IntCal();

		try
		{
		FileReader fr1=new FileReader("account.txt");
		BufferedReader br1=new BufferedReader(fr1);

		FileReader fr2=new FileReader("transaction.txt");
		BufferedReader br2=new BufferedReader(fr2);
		
		
		c.accList=c.getAllAccounts(br1);
		c.transacList=c.getAllTransactions(br2);
		

		for(int i=0;i<c.accList.size();i++)
		{
			System.out.println(c.accList.get(i).accNo+" "+c.accList.get(i).type+" "+c.accList.get(i).date+" "+c.accList.get(i).bal);
		}
		for(int i=0;i<c.transacList.size();i++)
		{
			System.out.println(c.transacList.get(i).transacId+" "+c.transacList.get(i).accNo+" "+c.transacList.get(i).date+" "+c.transacList.get(i).type+" "+c.transacList.get(i).amt);
		}
		System.out.println(c.getBalanceAsOnDate(1001,LocalDate.now()));
		}catch(Exception e){}
		
	
	}
	public List<Account> getAllAccounts(BufferedReader br)
	{
		List<Account>l=new ArrayList<>();
		String line;
		try
		{
		while((line=br.readLine())!=null)
		{
			String[] s=line.split(",");
			l.add(new Account(Integer.parseInt(s[0]),s[1],s[2],Double.parseDouble(s[3])));	
		}
		br.close();
		}
		catch(Exception e){}
		return l;
	}
	public List<Transaction> getAllTransactions(BufferedReader br)
	{
		List<Transaction>l=new ArrayList<>();
		String line;
		try{
		while((line=br.readLine())!=null)
		{
			String[] s=line.split(",");
			l.add(new Transaction(Integer.parseInt(s[0]),Integer.parseInt(s[1]),s[2],s[3].charAt(0),Double.parseDouble(s[4])));	
		}
		br.close();
		}
		catch(Exception e){}
		return l;
	}

	public double getMinBalance(int accNo,String mmyyyy,double bal)
	{
		double min=bal;
		String[] s=mmyyyy.split(" ");
		try{for(int i=0;i<transacList.size();i++)
		{
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate ldd=LocalDate.parse(transacList.get(i).date,fmt);

			int month=ldd.getMonth().getValue();
			int year=ldd.getYear();
			int num=ldd.getDayOfMonth();
			
			if((transacList.get(i).accNo==accNo) && Integer.parseInt(s[1])==year && Integer.parseInt(s[0])==month && num>=10)
			{
				if(transacList.get(i).type=='D')
					bal=bal+transacList.get(i).amt;
				else if(transacList.get(i).type=='W')
				{
					bal=bal-transacList.get(i).amt;	
					if(min>bal)
						min=bal;
				}
			}
			
		}}catch(Exception e){}
		return min;
	}

	public double balBeforeSixMonths(int accNo,LocalDate date,double bal)
	{
		LocalDate sd=date.minusMonths(6).minusDays(1);
		for(int i=0;i<transacList.size();i++)
		{
			try{
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate ldd=LocalDate.parse(transacList.get(i).date,fmt);
		
			int num=ldd.getDayOfMonth();
			if((transacList.get(i).accNo==accNo) && ldd.isAfter(sd) && ldd.isBefore(date))
			{	
				if(transacList.get(i).type=='D')
				{
					System.out.println("Eneterd roi");
					bal=bal-transacList.get(i).amt;
				}
				else if(transacList.get(i).type=='W')
				{
					bal=bal+transacList.get(i).amt;	
				}
				else
					bal=bal;
				
			}
			}catch(Exception e){}
		}
		return bal;
	}
	double finalInterest=0;
	public double getBalanceAsOnDate(int accNo,LocalDate date)
	{
		double balance=0,minbal=0,tamt=0;
		for(int i=0;i<accList.size();i++)
		{
			if(accList.get(i).accNo==accNo)
			{	
				balance=accList.get(i).bal;
				minbal=balance;
				break;	
			}
		}
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate ld=date.minusMonths(6);		//past six months from current date
		balance=balBeforeSixMonths(accNo,date,balance);
		int j=0;
		while(j<6)
		{
			int month=ld.plusMonths(j).getMonth().getValue();
			int year=ld.plusMonths(j).getYear();
			int num=ld.plusMonths(j).getDayOfMonth();
			double b=balance;
			System.out.println("month is "+month);
			try{for(int i=0;i<transacList.size();i++)
			{
				LocalDate td=LocalDate.parse(transacList.get(i).date,fmt);
				if(transacList.get(i).accNo==accNo && td.getMonth().getValue()==month)
				{
					if(transacList.get(i).type=='W')
						balance-=transacList.get(i).amt;
					else if(transacList.get(i).type=='D')
						balance+=transacList.get(i).amt;
				
				}
				if((td.getMonth().getValue()==month)&&(td.getDayOfMonth()<10))
				{
					if(transacList.get(i).type=='W')
						b-=transacList.get(i).amt;
					else if(transacList.get(i).type=='D')
						b+=transacList.get(i).amt;
				}
			} }catch(Exception e){}
			String s=Integer.toString(month)+" "+Integer.toString(year);
			System.out.println("getminbal()  "+getMinBalance(accNo,s,b));
			finalInterest+=calInterest(getMinBalance(accNo,s,b));
			System.out.println("Final interest is "+finalInterest);
			j++;
		}
		return finalInterest;	
	}
	public double calInterest(double prncpleAmt)
	{
		double interest=0,m=1.0/12.0;
		interest=(prncpleAmt*m*4.5)/100;
		return interest;
	}	
}
class Account
{
	protected int accNo;
	protected String type;
	protected String date;
	protected double bal;
	public Account(int accNo,String type,String date,double bal)
	{
		this.accNo=accNo;
		this.type=type;
		this.date=date;
		this.bal=bal;
	}
}
class Transaction
{
	protected int transacId;
	protected int accNo;
	protected String date;
	protected char type;
	protected double amt;
	public Transaction(int transacId,int accNo,String date,char type,double amt)
	{
		this.transacId=transacId;
		this.accNo=accNo;
		this.date=date;
		this.type=type;
		this.amt=amt;
	}
}
