package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
		String username = "plf_training_admin";
		String password = "pff123";

		try {
			// Load the PostgreSQL JDBC driver
			Class.forName("org.postgresql.Driver");

			// Create a connection to the database
			Connection connection = DriverManager.getConnection(url, username, password);

			// Check if the connection is successful
			if (connection != null) {
				System.out.println("Connected to the PostgreSQL database!");
				// Do your database operations here...
				// For example: execute queries, updates, etc.

				// Remember to close the connection when done
				connection.close();
			} else {
				System.out.println("Failed to connect to the database.");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}

	}

}
