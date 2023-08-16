import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Nonaction {
	public static void main(String args[]) {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");

			Connection cn = DriverManager.getConnection(url, username, password);

			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// String s5 = "create table raju(topname varchar(10),topid int,dept_no varchar(12))";
			String s1 = "select * from raju";
			String s2 = "insert into raju (topname,topid,dept_no) values(?,?,?)";
			PreparedStatement p = cn.prepareStatement(s2);
			int r = st.executeUpdate(s2);
			// System.out.println("no of rows is effectd:" + r);
			ResultSet rst = st.executeQuery(s1);
			while (rst.next()) {
				String a1 = rst.getString("topname");
				int a2 = rst.getInt("topid");
				String a3 = rst.getString("dept_no");
				System.out.println("employeee name.:  " + a1 + "empoyee id  " + a2 + "employee dept  " + a3);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
