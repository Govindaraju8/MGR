package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrainsDAL {
	private Connection connection;

	public TrainsDAL(Connection connection) {
		this.connection = connection;
	}

	public List<String> getAllTrains() throws Exception {
		List<String> trains = new ArrayList<>();
		try {
			String trainQuery = "SELECT train_name FROM trains_1";
			PreparedStatement statement = connection.prepareStatement(trainQuery);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				trains.add(resultSet.getString("train_name"));
			}
		} catch (Exception e) {
			throw e;
		}
		return trains;
	}
}