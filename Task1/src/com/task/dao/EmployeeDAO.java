package com.task.dao;
import java.util.List;

import com.task.model.Department;
import com.task.model.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public boolean updateEmployeeById(Employee emp);
	public List<Department> getAllDepartments();
	public boolean deleteEmployeeById(int id);
	public boolean addEmployee(Employee emp);
	public boolean deleteEmployeeByIds(List<Integer> ids);
	public List<Employee> searchByCriteria(String searchCriteria);
	public List<Employee> searchByEmpId(int empId);
	public List<Employee> searchEmployeeByNameEmail(String firstName, String lastName, String email);
}
