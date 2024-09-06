import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Step 1: Create an interface for employee operations
interface EmployeeOperations {
    void addEmployee(String name, String id, String department, String salary);
    String displayEmployees();
}

// Step 2: Implement the interface
class EmployeeDetails implements EmployeeOperations {
    private List<Employee> employeeList = new ArrayList<>();

    // Inner Employee class to store employee data
    class Employee {
        String name, id, department, salary;

        public Employee(String name, String id, String department, String salary) {
            this.name = name;
            this.id = id;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", ID: " + id + ", Dept: " + department + ", Salary: " + salary;
        }
    }

    @Override
    public void addEmployee(String name, String id, String department, String salary) {
        Employee employee = new Employee(name, id, department, salary);
        employeeList.add(employee);
    }

    @Override
    public String displayEmployees() {
        StringBuilder result = new StringBuilder();
        for (Employee emp : employeeList) {
            result.append(emp.toString()).append("\n");
        }
        return result.toString();
    }
}

// Step 3: Create the GUI using Java Swing
public class EmployeeEntryApp {
    private JFrame frame;
    private JTextField nameField, idField, departmentField, salaryField;
    private JTextArea displayArea;
    private EmployeeOperations operations;

    public EmployeeEntryApp() {
        operations = new EmployeeDetails();  // Initialize EmployeeOperations object

        // Create and set up the frame
        frame = new JFrame("Employee Entry System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));  // Input fields layout

        // Create UI components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel idLabel = new JLabel("Employee ID:");
        idField = new JTextField();
        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField();
        JLabel salaryLabel = new JLabel("Salary:");
        salaryField = new JTextField();

        JButton addButton = new JButton("Add Employee");
        JButton displayButton = new JButton("Display Employees");

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployees();
            }
        });

        // Add components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(departmentLabel);
        inputPanel.add(departmentField);
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);
        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        // Add inputPanel and scrollPane to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to add employee details
    private void addEmployee() {
        String name = nameField.getText();
        String id = idField.getText();
        String department = departmentField.getText();
        String salary = salaryField.getText();

        // Check if all fields are filled
        if (name.isEmpty() || id.isEmpty() || department.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.");
            return;
        }

        operations.addEmployee(name, id, department, salary);  // Add employee to the list

        JOptionPane.showMessageDialog(frame, "Employee Added Successfully!");

        // Clear fields after adding
        nameField.setText("");
        idField.setText("");
        departmentField.setText("");
        salaryField.setText("");
    }

    // Method to display all employees
    private void displayEmployees() {
        displayArea.setText(operations.displayEmployees());  // Display all employees
    }

    public static void main(String[] args) {
        // Run the Employee Entry App
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeEntryApp();
            }
        });
    }
}
