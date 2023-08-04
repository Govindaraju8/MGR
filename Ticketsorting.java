package king;

import java.util.ArrayList;
import java.util.Collections;

public class Ticketsorting {
	public static void main(String args[]) {
		ArrayList<Ticket> tickets = new ArrayList<>();
		// Ticket 1
		Ticket tt1 = new Ticket(1234, 3712, "27-06-2023", "vsp", "ppm");
		ArrayList<Passenger> passengers1 = new ArrayList<>();
		passengers1.add(new Passenger("ramu", 2, "m"));
		passengers1.add(new Passenger("sai", 8, "f"));
		passengers1.add(new Passenger("raju", 70, "m"));
		passengers1.add(new Passenger("govind", 42, "m"));
		tt1.setPassengers(passengers1);

		// Ticket 2
		Ticket tt2 = new Ticket(5678, 7812, "22-06-2023", "abc", "xyz");
		ArrayList<Passenger> passengers2 = new ArrayList<>();
		passengers2.add(new Passenger("john", 30, "m"));
		passengers2.add(new Passenger("jane", 25, "f"));
		passengers2.add(new Passenger("alice", 12, "f"));

		tt2.setPassengers(passengers2);

		// Ticket 3
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

		// Sort the tickets based on travel date
		Collections.sort(tickets);

		// Display sorted ticket details
		for (Ticket ticket : tickets) {
			ticket.displayTicketDetails();
			System.out.println("Passenger count: " + ticket.getPassengerCount());
			System.out.println("Total fare: " + ticket.getTotalFare());
			System.out.println("----------------------------");
		}

	}

	public static class Ticket implements Comparable<Ticket> {
		int TicketNo;
		int TrainNo;
		String TravelDate;
		String Source;
		String Destination;
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
			float totalFare = 0;
			for (Passenger passenger : passengers) {
				totalFare += passenger.gettc();
			}
			return totalFare;
		}

		@Override
		public int compareTo(Ticket other) {
            // First, compare by travel date
            int dateComparison = this.TravelDate.compareTo(other.TravelDate);
            if (dateComparison != 0) {
                return dateComparison;
            }

            // Next, compare by passenger count
            int passengerCountComparison = Integer.compare(this.getPassengerCount(), other.getPassengerCount());
            if (passengerCountComparison != 0) {
                return passengerCountComparison;
            }

            // Finally, compare by total fare
            float totalFareThis = this.getTotalFare();
            float totalFareOther = other.getTotalFare();
            return Float.compare(totalFareThis, totalFareOther);
        }

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

	public static class Passenger {
		private static float basePrice = 350;
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
}
