package in.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.employeeManagement.Exception.EmployeeNotFoundException;
import in.employeeManagement.assembler.EmployeeAssembler;
import in.employeeManagement.dao.EmployeeDAO;
import in.employeeManagement.dto.EmployeeDto;
import in.employeeManagement.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	@Transactional
	@Override
	public List<EmployeeDto> getAll() {
		List<Employee> emp = employeeDAO.getAll();
		List<EmployeeDto> empList = new ArrayList<EmployeeDto>();
		// TODO: Below line can be done using Stream classes
		for (Employee item : emp) {
			empList.add(EmployeeAssembler.toDTO(item));
		}
		return empList;
	}

	@Transactional
	@Override
	public EmployeeDto getEmployee(int id) {
		Employee emp = employeeDAO.getEmployee(id);
		if(null == emp) {
			throw new EmployeeNotFoundException("Employee Does not exist");
		}
		return EmployeeAssembler.toDTO(emp);
	}

	@Transactional
	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		// Convert employeeDto to entity class object
		Employee employee = EmployeeAssembler.toEntity(employeeDto);
		Employee emp = employeeDAO.save(employee);
		return EmployeeAssembler.toDTO(emp);
	}

	@Transactional
	@Override
	public void delete(int id) {
		Employee emp = employeeDAO.getEmployee(id);
		if(emp == null) {
			throw new EmployeeNotFoundException("Id does not exist. Enter correct Id!");
		}
		employeeDAO.delete(id);
	}

}
