package CalInterest;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class MainFrame extends Frame {
	private static final String DB_URL = "jdbc:postgresql://192.168.110.48/plf_training";
	private static final String DB_USER = "plf_training_admin";
	private static final String DB_PASSWORD = "pff123";
	private TextField empNoTextField;
	private TextField nameTextField;
	private TextField jobTextField;
	private TextField salaryTextField;
	private Choice departmentChoice;
	private int currentIndex = 0;
	private int totalRecords = 0;
	private List<String[]> records = new ArrayList<>();
	private boolean isEditMode = false;
	private static final Font BOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private Button addButton;
	private Button saveButton;
	private Button delButton;
	private Button searchButton;
	private DefaultTableModel tableModel;
	private JTable table;
	public MainFrame() {
		setTitle("Employee Master Entry");
		setSize(300, 400);
		setLayout(new BorderLayout());
		initializeGUI();
		tableModel = new DefaultTableModel(new String[] { "Emp No", "Name", "Job", "Salary", "Department" }, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		setModeUI();
		this.setBackground(Color.WHITE);
		setVisible(true);
		readDataFromDatabase();
	}
	private void initializeGUI() {
		Panel inputPanel = new Panel(new GridLayout(6, 2));
		Label empNoLabel = createBoldLabel("Emp No:");
		Label nameLabel = createBoldLabel("Name:");
		Label jobLabel = createBoldLabel("Job:");
		Label salaryLabel = createBoldLabel("Salary:");
		Label departmentLabel = createBoldLabel("Department:");
		empNoTextField = new TextField();
		nameTextField = new TextField();
		jobTextField = new TextField();
		salaryTextField = new TextField();
		departmentChoice = new Choice();
		departmentChoice.add("Development");
		departmentChoice.add("Sales");
		departmentChoice.add("Testing");
		inputPanel.add(empNoLabel);
		inputPanel.add(empNoTextField);
		inputPanel.add(nameLabel);
		inputPanel.add(nameTextField);
		inputPanel.add(jobLabel);
		inputPanel.add(jobTextField);
		inputPanel.add(salaryLabel);
		inputPanel.add(salaryTextField);
		inputPanel.add(departmentLabel);
		inputPanel.add(departmentChoice);
		add(inputPanel, BorderLayout.NORTH);
		Panel buttonPanel = new Panel(new GridLayout(5, 2));
		String[] buttonNames = { "First", "Next", "Prev", "Last", "Add", "Edit", "Del", "Save", "Search", "Clear",
		"Exit" };
		Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		for (String buttonName : buttonNames) {
			Button button = new Button(buttonName);
			button.setFont(buttonFont);
			button.setBackground(Color.YELLOW);
			button.setForeground(Color.BLACK);
			switch (buttonName) {
			case "Exit":
				button.addActionListener(e -> dispose());
				break;
			case "Clear":
				button.addActionListener(e -> clearFields());
				break;
			case "Add":
				addButton = button;
				addButton.setEnabled(false);
				addButton.addActionListener(e -> addDataToDatabase());
				break;
			case "First":
				button.addActionListener(e -> showFirstRecord());
				break;
			case "Last":
				button.addActionListener(e -> showLastRecord());
				break;
			case "Next":
				button.addActionListener(e -> showNextRecord());
				break;
			case "Prev":
				button.addActionListener(e -> showPreviousRecord());
				break;
			case "Edit":
				button.addActionListener(e -> toggleEditMode());
				break;
			case "Save":
				saveButton = button;
				saveButton.setEnabled(false);
				saveButton.addActionListener(e -> addDataToDatabase());
				break;
			case "Del":
				delButton = button;
				delButton.setEnabled(false);
				delButton.addActionListener(e -> deleteCurrentRecord());
				break;
			case "Search":
				searchButton = button;
				searchButton.setEnabled(false);
				searchButton.addActionListener(e -> searchRecord());
				break;
			}
			buttonPanel.add(button);
		}
		add(buttonPanel, BorderLayout.SOUTH);
	}
	private Label createBoldLabel(String text) {
		Label label = new Label(text);
		label.setFont(BOLD_FONT);
		label.setForeground(Color.BLUE);
		return label;
	}
	private void readDataFromDatabase() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sqlQuery = "SELECT * FROM king_emp";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(sqlQuery)) {
				records.clear();
				totalRecords = 0;
				tableModel.setRowCount(0);
				while (resultSet.next()) {
					String empNo = resultSet.getString("empno");
					String name = resultSet.getString("ename");
					String job = resultSet.getString("job");
					String salary = resultSet.getString("sal");
					String department = resultSet.getString("dept_no");
					String[] data = { empNo, name, job, salary, department };
					records.add(data);
					totalRecords++;
					tableModel.addRow(data);
				}

				if (totalRecords > 0) {
					currentIndex = 0;
					displayRecord(currentIndex);
				} else {
					clearFields();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void clearFields() {
		empNoTextField.setText("");
		nameTextField.setText("");
		jobTextField.setText("");
		salaryTextField.setText("");
		departmentChoice.select(0);
	}
	private void addDataToDatabase() {
		String empNo = empNoTextField.getText().trim();
		String name = nameTextField.getText().trim();
		String job = jobTextField.getText().trim();
		String salary = salaryTextField.getText().trim();
		String department = departmentChoice.getSelectedItem();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sqlQuery = "INSERT INTO emp (emp_no, name, job, salary, department) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, empNo);
				statement.setString(2, name);
				statement.setString(3, job);
				statement.setString(4, salary);
				statement.setString(5, department);

				int rowsAffected = statement.executeUpdate();
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(this, "Data added to database successfully!");
					clearFields();
					readDataFromDatabase(); // Refresh the data in the table
				} else {
					JOptionPane.showMessageDialog(this, "Data insertion failed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void displayRecord(int index) {
		if (index >= 0 && index < totalRecords) {
			String[] data = records.get(index);
			empNoTextField.setText(data[0].trim());
			nameTextField.setText(data[1].trim());
			jobTextField.setText(data[2].trim());
			salaryTextField.setText(data[3].trim());
			departmentChoice.select(data[4].trim());
			table.setRowSelectionInterval(index, index);
			table.scrollRectToVisible(table.getCellRect(index, 0, true));
		}
	}
	private void setModeUI() {
		empNoTextField.setEditable(isEditMode);
		nameTextField.setEditable(isEditMode);
		jobTextField.setEditable(isEditMode);
		salaryTextField.setEditable(isEditMode);
		departmentChoice.setEnabled(isEditMode);
	}
	private void deleteCurrentRecord() {
		if (totalRecords > 0) {
			records.remove(currentIndex);
			totalRecords--;

			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				String sqlQuery = "DELETE FROM emp WHERE emp_no = ?";
				try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
					String empNoToDelete = empNoTextField.getText().trim();
					statement.setString(1, empNoToDelete);
					int rowsAffected = statement.executeUpdate();

					if (rowsAffected > 0) {
						if (totalRecords > 0) {
							if (currentIndex >= totalRecords) {
								currentIndex = totalRecords - 1;
							}
							displayRecord(currentIndex);
						} else {
							clearFields();
							JOptionPane.showMessageDialog(this, "All records deleted.");
						}
						// Remove the corresponding row from the table model
						tableModel.removeRow(currentIndex);

						JOptionPane.showMessageDialog(this, "Record deleted successfully.");
					} else {
						JOptionPane.showMessageDialog(this, "Failed to delete the record.");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void showFirstRecord() {
		if (totalRecords > 0) {
			if (currentIndex == 0) {
				JOptionPane.showMessageDialog(this, "Already at the first record.");
			} else {
				currentIndex = 0;
				displayRecord(currentIndex);
			}
		}
	}
	private void showLastRecord() {
		if (totalRecords > 0) {
			if (currentIndex == totalRecords - 1) {
				JOptionPane.showMessageDialog(this, "Already at the last record.");
			} else {
				currentIndex = totalRecords - 1;
				displayRecord(currentIndex);
			}
		}
	}
	private void showNextRecord() {
		if (totalRecords > 0) {
			if (currentIndex == totalRecords - 1) {
				JOptionPane.showMessageDialog(this, "Already at the last record.");
			} else {
				currentIndex++;
				displayRecord(currentIndex);
			}
		}
	}
	private void showPreviousRecord() {
		if (totalRecords > 0) {
			if (currentIndex == 0) {
				JOptionPane.showMessageDialog(this, "Already at the first record.");

			} else {
				currentIndex--;
				displayRecord(currentIndex);
			}
		}
	}
	private void toggleEditMode() {
		isEditMode = !isEditMode;
		setModeUI();
		updateButtonEnabledStates();
	}
	private void searchRecord() {
		String empNo = empNoTextField.getText().trim();
		String name = nameTextField.getText().trim();
		String job = jobTextField.getText().trim();
		String salary = salaryTextField.getText().trim();
		String department = departmentChoice.getSelectedItem();
		if (empNo.isEmpty() && name.isEmpty() && job.isEmpty() && salary.isEmpty() && department.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a search term.");
			return;
		}
		int foundIndex = -1;
		for (int i = 0; i < totalRecords; i++) {
			String[] record = records.get(i);
			if ((!empNo.isEmpty() && record[0].equals(empNo)) ||
					(!name.isEmpty() && record[1].equals(name)) ||
					(!job.isEmpty() && record[2].equals(job)) ||
					(!salary.isEmpty() && record[3].equals(salary)) ||
					(!department.isEmpty() && record[4].equals(department))) {
				foundIndex = i;
				break;
			}
		}
		if (foundIndex != -1) {
			currentIndex = foundIndex;
			displayRecord(currentIndex);
			JOptionPane.showMessageDialog(this, "Record found.");
		} else {
			clearFields();
			JOptionPane.showMessageDialog(this, "Record not found.");
		}
	}
	private void updateButtonEnabledStates() {
		addButton.setEnabled(isEditMode);
		saveButton.setEnabled(isEditMode);
		delButton.setEnabled(isEditMode);
		searchButton.setEnabled(isEditMode);
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}

