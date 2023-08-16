
package jdbc_example1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class meta_data {

	public static void main(String[] args) {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet tables = metaData.getColumns(null, null, "example123", null);

			while (tables.next()) {
				String columnName = tables.getString("COLUMN_NAME");
				String columntype = tables.getString("DATA_TYPE");

				System.out.println("Table Name: " + columnName + "  " + " COLUMN TYPE " + columntype);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
