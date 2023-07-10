package CriticalThinking.Module7;

import java.util.List;

public class ScopeMinimizationDiscussion {
	

	public class PayrollManager {
	    private List<Employee> employees;

	    public PayrollManager(List<Employee> employees) {
	        this.employees = employees;
	    }

	    public void calculatePayroll() {
	        for (Employee employee : employees) {
	            double salary = employee.getSalary();

	            double tax = calculateTax(salary);
	            double deductions = calculateDeductions(salary);
	            double netSalary = salary - tax - deductions;

	            System.out.println("Employee: " + employee.getName());
	            System.out.println("Position: " + employee.getPosition());
	            System.out.println("Net Salary: " + netSalary);
	            System.out.println("-----------------------");
	        }
	    }

	    private double calculateTax(double salary) {
	        // Calculate tax based on the salary
	        // ...
	        return salary * 0.2; // Assuming a fixed tax rate of 20%
	    }

	    private double calculateDeductions(double salary) {
	        // Calculate deductions based on the salary
	        // ...
	        return salary * 0.1; // Assuming a fixed deduction rate of 10%
	    }
	}
	
	public class Employee {
	    private String name;
	    private String position;
	    private double salary;

	    public Employee(String name, String position, double salary) {
	        this.name = name;
	        this.position = position;
	        this.salary = salary;
	    }

	    public double getSalary() {
	    	return this.salary;
	    }
	    
	    public String getName() {
	    	return this.name;
	    }
	    
	    public String getPosition() {
	    	return this.position;
	    }
	}

}


