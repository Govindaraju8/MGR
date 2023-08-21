package dal;


import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Ticket;

public class BookingDAL {
	private Connection connection;

	public BookingDAL(Connection connection) {
		this.connection = connection;
	}

	public void bookTicket(Ticket ticket) throws Exception {
		try {
			// Insert the ticket information and passenger details into the database
			String insertQuery = "INSERT INTO tickets (from_station, to_station, train, date, class) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, ticket.getFromStation());
			statement.setString(2, ticket.getToStation());
			statement.setString(3, ticket.getTrain());
			statement.setString(4, ticket.getDate());
			statement.setString(5, ticket.getTrainClass());
			statement.executeUpdate();

			// Insert passenger details
			// ...

		} catch (Exception e) {
			throw e;
		}
	}
}