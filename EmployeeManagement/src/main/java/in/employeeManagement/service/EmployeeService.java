package in.employeeManagement.service;

import java.util.List;

import in.employeeManagement.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAll();

	EmployeeDto getEmployee(int id);

	EmployeeDto save(EmployeeDto employeeDto);

	void delete(int id);
}
