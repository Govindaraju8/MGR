package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dal.TrainsDAL;


public class TrainsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = null;

		try {
			String jdbcUrl = "jdbc:postgresql://192.168.110.48/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			TrainsDAL trainsDAL = new TrainsDAL(connection);
			List<String> trains = trainsDAL.getAllTrains();
			JSONArray jsonArray = new JSONArray();
			for (String train : trains) {
				jsonArray.put(train);
			}

			// Write the JSON array as a string to the response
			response.setContentType("application/json");
			response.getWriter().write(jsonArray.toString());

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}