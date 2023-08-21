package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAL {
	private Connection connection;

	public ClassesDAL(Connection connection) {
		this.connection = connection;
	}

	public List<String> getAllClasses() throws Exception {
		List<String> classes = new ArrayList<>();
		try {
			String classQuery = "SELECT class_name FROM classes";
			PreparedStatement statement = connection.prepareStatement(classQuery);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				classes.add(resultSet.getString("class_name"));
			}
		} catch (Exception e) {
			throw e;
		}
		return classes;
	}
}