package in.employeeManagement.dao;

import java.util.List;

import in.employeeManagement.model.Employee;

public interface EmployeeDAO {

	List<Employee> getAll();

	Employee getEmployee(int id);

	Employee save(Employee employee);

	void delete(int id);
}
