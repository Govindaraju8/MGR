package model;

public class Ticket {
	private String fromStation;
	private String toStation;
	private String train;
	private String date;
	private String trainClass;
	private String classId;
	private PassengerList passengers;
	public Ticket(String fromStation, String toStation, String train, String date, String trainClass,
			String classId,PassengerList passengers ) {
		super();
		this.setFromStation(fromStation);
		this.setToStation(toStation);
		this.setTrain(train);
		this.setDate(date);
		this.setTrainClass(trainClass);
		this.setPassengers(passengers);
		this.setClassId(classId);
	}
	public Ticket() {
		// TODO Auto-generated constructor stub
	}
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTrainClass() {
		return trainClass;
	}
	public void setTrainClass(String trainClass) {
		this.trainClass = trainClass;
	}
	public PassengerList getPassengers() {
		return passengers;
	}
	public void setPassengers(PassengerList passengers) {
		this.passengers = passengers;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId=classId;
	}


	// Add constructors, getters, and setters
}
