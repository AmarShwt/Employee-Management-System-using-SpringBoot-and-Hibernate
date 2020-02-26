package in.employeeManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.employeeManagement.Exception.EmployeeNotFoundException;
import in.employeeManagement.dto.EmployeeDto;
import in.employeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployee() {
		try {
			List<EmployeeDto> listEmp = employeeService.getAll();
			return new ResponseEntity<>(listEmp, HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>("Something went wrong" + exp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody EmployeeDto employeeDto) {
		try {
			employeeService.save(employeeDto);
			return new ResponseEntity<>("Employee details saved sucessfully", HttpStatus.OK);
		} catch (Exception exp) {
			return new ResponseEntity<>("Something went wrong" + exp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//Look into this 
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {

		try {
			EmployeeDto employeeObj = employeeService.getEmployee(id);
			return new ResponseEntity<EmployeeDto>(employeeObj, HttpStatus.OK);
		} catch (EmployeeNotFoundException exp) {
			return new ResponseEntity<>(exp.getMessage(), HttpStatus.NO_CONTENT);
		} 
		catch (Exception exp) {
			return new ResponseEntity<>("Something went wrong"+exp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {

		try {
			employeeService.delete(id);
			return new ResponseEntity<>("Employee with Id :" + id + " has been deleted sucessfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Employee with id:" + id + " not found", HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		try {
			employeeService.save(employeeDto);
			System.out.println(employeeDto.toString());
			return new ResponseEntity<>("Employee has been updated sucessfully", HttpStatus.OK);
		} catch (Exception exp) {
			return new ResponseEntity<>("Employee does not found", HttpStatus.NO_CONTENT);
		}
	}
}

/**
 * TODO: Input data validations 
 * Logger
 * Additional TODOs for Backend: 1. Add Department as a separate table. Maintain
 * foreign keys and perform joints 2. List employees by department ID 3. API
 * Authentications using hardcoded string value (API request should have
 * username and password as 'password') 4. Add Loggers 5. Authentications using
 * JWT TokensJWT 6. Use AWS services 7. Design Patterns NOTE: APIs should always
 * have only input validations and exception handling and returning code.. No
 * business logics
 */
