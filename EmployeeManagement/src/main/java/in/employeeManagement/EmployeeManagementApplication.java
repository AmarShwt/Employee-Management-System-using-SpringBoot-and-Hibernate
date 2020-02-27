package in.employeeManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeManagementApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
		LOGGER.debug("This is Debug messsage");
		LOGGER.info("This is Info messsage");
		LOGGER.warn("This is Warn messsage");
		LOGGER.error("This is Error messsage");		
	}

}
