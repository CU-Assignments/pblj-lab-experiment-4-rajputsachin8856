import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Salary=" + salary;
    }
}

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(int id, String name, double salary) {
        for (Employee e : employees) {
            if (e.id == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        Employee newEmployee = new Employee(id, name, salary);
        employees.add(newEmployee);
        System.out.println("Employee Added: " + newEmployee);
    }

    public void updateEmployee(int id, double newSalary) {
        for (Employee e : employees) {
            if (e.id == id) {
                e.salary = newSalary;
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    public void removeEmployee(int id) {
        for (Employee e : employees) {
            if (e.id == id) {
                employees.remove(e);
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    public void searchEmployee(String searchTerm) {
        boolean found = false;
        for (Employee e : employees) {
            if (String.valueOf(e.id).equals(searchTerm) || e.name.equalsIgnoreCase(searchTerm)) {
                System.out.println("Employee Found: " + e);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Error: Employee not found.");
        }
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManagementSystem system = new EmployeeManagementSystem();

        System.out.println("Test Case 1: Display Employees (No Employees Initially)");
        system.displayEmployees();

        System.out.println("\nTest Case 2: Add Employees");
        system.addEmployee(101, "Anish", 50000);
        system.addEmployee(102, "Bobby", 60000);

        System.out.println("\nTest Case 3: Update Employee Salary");
        system.updateEmployee(101, 55000);

        System.out.println("\nTest Case 4: Search Employee by ID");
        system.searchEmployee("102");

        System.out.println("\nTest Case 5: Remove Employee");
        system.removeEmployee(101);

        System.out.println("\nTest Case 6: Display All Employees");
        system.displayEmployees();

        System.out.println("\nTest Case 7: Adding Duplicate Employee ID");
        system.addEmployee(101, "Charlie", 70000);
    }
}

