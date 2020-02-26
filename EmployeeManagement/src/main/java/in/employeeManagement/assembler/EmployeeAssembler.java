package in.employeeManagement.assembler;

import in.employeeManagement.dto.EmployeeDto;
import in.employeeManagement.model.Employee;

public class EmployeeAssembler {
	
	public static Employee toEntity(EmployeeDto employeeDto) {
		
		Employee employee=new Employee();
		employee.setId(employeeDto.getId());
		employee.setName(employeeDto.getName());
		employee.setGender(employeeDto.getGender());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setDob(employeeDto.getDob());
	
		return employee;
	}
	
	public static EmployeeDto toDTO(Employee employee) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setName(employee.getName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setDob(employee.getDob());
		
		return employeeDto;
	}
}
