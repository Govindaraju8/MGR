package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StationsDAL {
	private Connection connection;

	public StationsDAL(Connection connection) {
		this.connection = connection;
	}

	public List<String> getAllStations() throws Exception {
		List<String> stations = new ArrayList<>();
		try {
			String stationQuery = "SELECT station_name FROM stations_1";
			PreparedStatement statement = connection.prepareStatement(stationQuery);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stations.add(resultSet.getString("station_name"));
			}
		} catch (Exception e) {
			throw e;
		}
		return stations;
	}
}