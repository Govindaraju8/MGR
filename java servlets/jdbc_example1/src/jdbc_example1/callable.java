package jdbc_example1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class callable {

	public static void main(String[] args) {
		try {

			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");

			Connection c = DriverManager.getConnection(url, username, password);

			String call = "{call my_procd(?,?,?)}";
			CallableStatement cs = c.prepareCall(call);

			cs.setInt(1, 12);
			cs.setString(2, "hello");
			cs.setInt(3, 13);
			cs.execute();

			// Retrieve and print table values using PreparedStatement
			String sql = "SELECT * FROM example123";
			PreparedStatement s = c.prepareStatement(sql);
			ResultSet rst = s.executeQuery();

			int r = cs.getUpdateCount();
			System.out.println("no of lines get effected....." + r);

			while (rst.next()) {

				int stdid = rst.getInt("std_id");
				String stdname = rst.getString("std_name");

				int section = rst.getInt("std_dept");

				System.out.println(
						"student name...." + stdname + "   student id......" + stdid + "  section name....." + section);
			}
			s.close();
			cs.close();
			rst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}