package model;

import java.util.ArrayList;
import java.util.List;

public class PassengerList {
	private List<Passenger> passengers = new ArrayList<>();

	public PassengerList(List<Passenger> passengers) {
		super();
		this.passengers = passengers;
	}

	public PassengerList() {
		// TODO Auto-generated constructor stub
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}


}