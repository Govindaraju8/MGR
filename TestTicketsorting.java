import java.util.*;
class Ticket{
		int TicketNo;
		int TrainNo;
		String TravelDate;
		String Source;
		String Destination;
		int passengercount;
		float totalfare;
		ArrayList<Passenger> passengers;

		public Ticket(int TNo, int TrNo, String TD, String Src, String Dest) {
			TicketNo = TNo;
			TrainNo = TrNo;
			TravelDate = TD;
			Source = Src;
			Destination = Dest;
		}

		public void setPassengers(ArrayList<Passenger> p) {
			passengers = p;
			this.passengercount=p.size();
			for(Passenger pa:p)
			{
			    this.totalfare=this.totalfare+pa.gettc();
			}
			
		}

		public int getTicketno() {
			return TicketNo;
		}

		public int getTrainno() {
			return TrainNo;
		}

		public String getTraveldate() {
			return TravelDate;
		}

		public String getsource() {
			return Source;
		}

		public String getdest() {
			return Destination;
		}
		
		public int getPassengerCount() {
			return passengers.size();
		}
		
		public float getTotalFare() {
			return this.totalfare;
		}
/*
        public int compareTo(Ticket newone)
        {
            //return (this.TravelDate.compareTo(newone.TravelDate));
            //return Integer.compare(this.getPassengerCount(), newone.getPassengerCount());
            //return Float.compare(this.getTotalFare(),newone.getTotalFare());
            return this.passengercount-newone.passengercount;
            
        }*/
		
		public void displayTicketDetails() {
			System.out.println("TicketNo :" + TicketNo);
			System.out.println("TrainNo :" + TrainNo);
			System.out.println("TravelDate :" + TravelDate);
			System.out.println("Source :" + Source);
			System.out.println("Destination :" + Destination);
			for (Passenger t : passengers) {
				System.out.println("passenger name:" + " " + t.getname() + " " + "age :" + t.getage() + " " + "gender :"
						+ " " + t.getgender() + " " + t.gettc());
			}
		}
	}
class Passenger {
		private float basePrice = 350;
		private String Name;
		private int Age;
		private String Gender;
		private float ticketCost;

		public Passenger(String n, int a, String g) {
			Name = n;
			Age = a;
			Gender = g;
		}

		public String getname() {
			return Name;
		}

		public int getage() {
			return Age;
		}

		public String getgender() {
			return Gender;
		}

		public float gettc() {
			if ((Age > 65 && Gender == "m") || (Age > 58 && Gender == "f"))
				ticketCost = basePrice * 0.75f;
			else if (Age < 5)
				ticketCost = 0;
			else if (Age >= 5 && Age <= 12)
				ticketCost = basePrice * 0.50f;
			else
				ticketCost = basePrice;
			return ticketCost;
		}
	}
	
public class TestTicketsorting
{
  public static void main(String args[])
  {
      
     ArrayList<Ticket> tickets=new ArrayList<>();
      //tt.displayTicketDetails();
      Ticket tt1 = new Ticket(1234, 3712, "27-06-2023", "vsp", "ppm");
		ArrayList<Passenger> passengers1 = new ArrayList<>();
		passengers1.add(new Passenger("ramu", 2, "m"));
		passengers1.add(new Passenger("sai", 8, "f"));
		passengers1.add(new Passenger("raju", 70, "m"));
		passengers1.add(new Passenger("govind", 42, "m"));
		tt1.setPassengers(passengers1);
        // tt1.displayTicketDetails();
        // tt1.getTotalFare();
         //System.out.print(tt1.getPassengerCount());
        Ticket tt2 = new Ticket(5678, 7812, "22-06-2023", "abc", "xyz");
		ArrayList<Passenger> passengers2 = new ArrayList<>();
		passengers2.add(new Passenger("john", 30, "m"));
		passengers2.add(new Passenger("jane", 25, "f"));
		passengers2.add(new Passenger("alice", 12, "f"));
		tt2.setPassengers(passengers2);
		
		Ticket tt3 = new Ticket(9876, 9123, "19-06-2023", "mnp", "qrs");
		ArrayList<Passenger> passengers3 = new ArrayList<>();
		passengers3.add(new Passenger("bob", 45, "m"));
		passengers3.add(new Passenger("mary", 50, "f"));
		passengers3.add(new Passenger("alex", 18, "m"));
		passengers3.add(new Passenger("bhart", 8, "m"));
		passengers3.add(new Passenger("pavan", 10, "m"));
		tt3.setPassengers(passengers3);
		
		tickets.add(tt1);
		tickets.add(tt2);
		tickets.add(tt3);
		// sorting the tickets using anonymous class with comparartor interface 
		
		Collections.sort(tickets, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				return -t1.getPassengerCount() + t2.getPassengerCount();
			}
		});
		
		Collections.sort(tickets, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				return (int) (t1.getTotalFare() - t2.getTotalFare());
			}
		});
		
		Collections.sort(tickets, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				return t1.getTraveldate().compareTo(t2.getTraveldate());
			}
		});
		
		
		// sorting using totalticketfare with lambda expression
		Collections.sort(tickets,(a, b) -> (int) (a.getTotalFare() - b.getTotalFare()));
		
		//sorting using tickets using traveldate with lambda expression
		Collections.sort(tickets,(a, b) -> a.getTraveldate().compareTo(b.getTraveldate()));
		
		//sorting the tickets using passenger count with lambda expression
		Collections.sort(tickets,(a, b) -> (-b.getPassengerCount() + a.getPassengerCount()));
		
		for (Ticket t : tickets) {
			t.displayTicketDetails();
			System.out.println("Passenger count: " + t.getPassengerCount());
			System.out.println("Total fare: " + t.getTotalFare());
			System.out.println("----------------------------");
		}
  }
}