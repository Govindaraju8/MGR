package jdbc_example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class batch_executions {

	public static void main(String[] args) {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);

			String insertSql = "INSERT INTO example123(std_id, std_name, std_dept) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

			// Add multiple rows to the batch
			for (int i = 1; i <= 10; i++) {
				preparedStatement.setInt(1, i);
				preparedStatement.setString(2, "Employee " + i);
				preparedStatement.setDouble(3, 50000 + i * 1000);
				preparedStatement.addBatch();
			}

			// Execute the batch
			int[] batchResult = preparedStatement.executeBatch();
			for (int i = 0; i < batchResult.length; i++) {
				System.out.println(batchResult[i]);
			}

			// Print the number of rows affected by each statement
			for (int result : batchResult) {
				System.out.println("Rows affected: " + result);
			}

			// Retrieve and print the inserted data
			String selectSql = "SELECT * FROM example123";
			ResultSet resultSet = connection.createStatement().executeQuery(selectSql);

			System.out.println("Inserted data:");
			while (resultSet.next()) {
				int id = resultSet.getInt("std_id");
				String name = resultSet.getString("std_name");
				double salary = resultSet.getDouble("std_dept");

				System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
			}

			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
