import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class MainFrame extends Frame {
    private TextField empNoTextField;
    private TextField nameTextField;
    private TextField jobTextField;
    private TextField salaryTextField;
    private Choice departmentChoice;
    private int currentIndex = 0;
    private int totalRecords = 0;
    private List<String[]> records = new ArrayList<>();
    private boolean isEditMode = false;
    private static final String DATA_FILE_PATH = "D:\\Eclipse_workplace\\JavaTraining\\src\\Training\\Emp.txt";
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
        readDataFromFile(DATA_FILE_PATH);
        setModeUI();
        this.setBackground(Color.WHITE);
        setVisible(true);
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
                     addButton.addActionListener(e -> addDataToFile());
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
                     saveButton.addActionListener(e -> addDataToFile());
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
    private void readDataFromFile(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            records.clear();
            totalRecords = 0;
            tableModel.setRowCount(0); 
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String[] data = new String[tokenizer.countTokens()];
                int index = 0;
                while (tokenizer.hasMoreTokens()) {
                    data[index] = tokenizer.nextToken();
                    index++;
                }
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }
    private void writeDataToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to update data file: " + e.getMessage());
        }
    }
    private void clearFields() {
        empNoTextField.setText("");
        nameTextField.setText("");
        jobTextField.setText("");
        salaryTextField.setText("");
        departmentChoice.select(0);
    }
    private void addDataToFile() {
        String empNo = empNoTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String job = jobTextField.getText().trim();
        String salary = salaryTextField.getText().trim();
        String department = departmentChoice.getSelectedItem();
        if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
        	if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
                if (!isEmpNoUnique(empNo)) {
                    JOptionPane.showMessageDialog(this, "Duplicate empNo. Please enter a unique empNo.");
                    return;
                }
                String[] data = { empNo, name, job, salary, department };
                records.add(data);
                totalRecords++;
                writeDataToFile(DATA_FILE_PATH);
                JOptionPane.showMessageDialog(this, "Data added to file successfully!");
                clearFields();
                updateTable(); // Update the table with the latest data
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            }
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
    private boolean isEmpNoUnique(String empNo) {
        for (String[] record : records) {
            if (record[0].equals(empNo)) {
                return false; 
            }
        }
        return true; 
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
    	        writeDataToFile(DATA_FILE_PATH);   
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
    	        clearFields();
    	    }
    	}
    private void saveDataToFile() {
        String empNo = empNoTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String job = jobTextField.getText().trim();
        String salary = salaryTextField.getText().trim();
        String department = departmentChoice.getSelectedItem();
        if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
            if (!isEmpNoUnique(empNo)) {
                JOptionPane.showMessageDialog(this, "Duplicate empNo. Please enter a unique empNo.");
                return;
            } 
            String[] data = { empNo, name, job, salary, department };
            records.set(currentIndex, data);
            writeDataToFile(DATA_FILE_PATH);
            JOptionPane.showMessageDialog(this, "Data saved to file successfully!");
            updateTable(); 
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
        }
    }
    private void updateTable() {
        tableModel.setRowCount(0); 
        for (String[] record : records) {
            tableModel.addRow(record); 
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
        MainFrame frame = new MainFrame();
    }
}
