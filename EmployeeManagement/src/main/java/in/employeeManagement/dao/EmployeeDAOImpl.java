package in.employeeManagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.employeeManagement.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> getAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		List<Employee> list = query.getResultList();
		return list;
	}

	@Override
	public Employee getEmployee(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Employee.class, id);
	}

	@Override
	public Employee save(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(employee);
		return employee;
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employeeObj = currentSession.get(Employee.class, id);
		currentSession.delete(employeeObj);
	}

}
