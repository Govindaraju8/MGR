

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:postgresql://192.168.110.48/plf_training";
	private static final String DB_USER = "plf_training_admin";
	private static final String DB_PASSWORD = "pff123";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("getEmployeeData".equals(action)) {
			List<Map<String, Object>> employees = new ArrayList<>();

			try {
				// Load the PostgreSQL driver class
				Class.forName("org.postgresql.Driver");
				try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					String sqlQuery = "SELECT * FROM king_emp";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sqlQuery)) {

						while (resultSet.next()) {
							Map<String, Object> employee = new HashMap<>();
							employee.put("empNo", resultSet.getInt("empno"));
							employee.put("name", resultSet.getString("ename"));
							employee.put("job", resultSet.getString("job"));
							employee.put("salary", resultSet.getInt("sal"));
							employee.put("department", resultSet.getInt("dept_no"));
							employees.add(employee);
						}

						// Convert the List to JSON using JsonArray and JsonObject
						JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
						for (Map<String, Object> employee : employees) {
							JsonObject jsonObject = Json.createObjectBuilder()
									.add("empNo", (Integer) employee.get("empNo"))
									.add("name", (String) employee.get("name"))
									.add("job", (String) employee.get("job"))
									.add("salary", (Integer) employee.get("salary"))
									.add("department", (Integer) employee.get("department"))
									.build();
							jsonArrayBuilder.add(jsonObject);
						}

						JsonArray jsonArray = jsonArrayBuilder.build();

						response.setContentType("application/json");
						PrintWriter out = response.getWriter();
						out.println(jsonArray.toString());
					}
				} catch (SQLException e) {
					e.printStackTrace();
					// Handle exception appropriately
				}
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				// Handle exception appropriately
			}
		}

		else if ("addEmployee".equals(action) || "editEmployee".equals(action)) {
			String empNo = request.getParameter("empNo");
			String name = request.getParameter("name");
			String job = request.getParameter("job");
			String salary = request.getParameter("salary");
			String department = request.getParameter("department");

			try {
				// Load the PostgreSQL driver class
				Class.forName("org.postgresql.Driver");

				// Establish the database connection
				try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					String sqlQuery;
					PreparedStatement preparedStatement;

					if ("addEmployee".equals(action)) {
						sqlQuery = "INSERT INTO king_emp(empno, ename, job, sal, dept_no) VALUES (?, ?, ?, ?, ?)";
						preparedStatement = connection.prepareStatement(sqlQuery);
						preparedStatement.setInt(1, Integer.parseInt(empNo));
						preparedStatement.setString(2, name);
						preparedStatement.setString(3, job);
						preparedStatement.setDouble(4, Double.parseDouble(salary));
						preparedStatement.setInt(5, Integer.parseInt(department));
					} else if ("editEmployee".equals(action)) {
						sqlQuery = "UPDATE king_emp SET ename = ?, job = ?, sal = ?, dept_no = ? WHERE empno = ?";
						preparedStatement = connection.prepareStatement(sqlQuery);
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, job);
						preparedStatement.setDouble(3, Double.parseDouble(salary));
						preparedStatement.setInt(4, Integer.parseInt(department));
						preparedStatement.setInt(5, Integer.parseInt(empNo));
					} else {
						throw new IllegalArgumentException("Invalid action");
					}

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						response.setContentType("text/plain");
						PrintWriter out = response.getWriter();
						out.print("Success");
					} else {
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database operation failed");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database driver not found");
			}
		}
		else if ("deleteEmployee".equals(action)) {
			String empNo = request.getParameter("empNo");

			try {
				// Load the PostgreSQL driver class
				Class.forName("org.postgresql.Driver");

				// Establish the database connection
				try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					String sqlQuery = "DELETE FROM king_emp WHERE empno = " + Integer.parseInt(empNo);





					try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {


						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							response.setContentType("text/plain");
							PrintWriter out = response.getWriter();
							out.print("Success");
						} else {
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database operation failed");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database driver not found");
			}
		}
	}




}
