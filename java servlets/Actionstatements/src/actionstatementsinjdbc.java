import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class actionstatementsinjdbc {
	public static void main(String args[]) {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");

			Connection cn = DriverManager.getConnection(url, username, password);

			Statement st = cn.createStatement();
			String s1 = "select * from potti";
			// insertion operation example
			String s2 = "insert into potti (empid,ename,sal) values(52,'bakka',2444)";
			String s3 = "update potti set sal=4000 where empid=52";
			String s4 = "delete from potti where ename='potti1'";
			// update

			int r = st.executeUpdate(s4);
			// int r = st.executeUpdate(s3);
			// int r = st.executeUpdate(s2);
			System.out.println("effected rows:" + r);
			ResultSet rst = st.executeQuery(s1);

			while (rst.next()) {
				int empno = rst.getInt("empid");

				String ename = rst.getString("ename");
				double sal = rst.getDouble("sal");
				System.out.println("empno :" + empno + " name :" + ename + " sal :" + sal);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
