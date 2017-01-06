package com.task.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.task.constants.SQLConstants;
import com.task.model.Department;
import com.task.model.Employee;
import com.task.util.DateTimeFormatUtil;
import com.task.util.HibernateUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public List<Employee> getAllEmployees() {
		try {
//			jpql
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			Query query = em.createQuery(SQLConstants.GET_ALL_EMPLOEES);
			
//			hql
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(SQLConstants.GET_ALL_EMPLOEES);
//			Query query = session.createSQLQuery(SQLConstants.GET_ALL_EMPLOYEES_NATIVE);
			@SuppressWarnings("unchecked")
			List<Employee> empList = query.list();
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(int id) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			Query query = em.createQuery(SQLConstants.GET_EMPLOEE_BY_ID).setParameter("empId", id);
			
//			hql
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(SQLConstants.GET_EMPLOEE_BY_ID).setParameter("empId", id);
			Employee emp = (Employee) query.uniqueResult();
			if(null != emp.getDob()){
				emp.setDobAsString(DateTimeFormatUtil.DateAsDDMMYYYY(emp.getDob()));
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEmployeeById(Employee emp) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			hql
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query q = session.createQuery(SQLConstants.UPDATE_EMP_BY_ID);
			q.setParameter("firstName", emp.getFirstName());
			q.setParameter("lastName", emp.getLastName());
			q.setParameter("dob", emp.getDob());
			q.setParameter("address", emp.getAddress());
			q.setParameter("email", emp.getEmail());
			q.setParameter("id", emp.getId());
			q.setParameter("department", emp.getDepartment());
			int n = q.executeUpdate();
			session.getTransaction().commit();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Department> getAllDepartments() {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			hql
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			Query query = session.createQuery(SQLConstants.GET_ALL_DEPTS);
			@SuppressWarnings("unchecked")
			List<Department> depts = query.list();
			return depts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			hql
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			session.getTransaction().begin();
			Query query = session.createQuery(SQLConstants.DELETE_EMP_BY_ID);
			query.setParameter("id", id);
			int i = query.executeUpdate();
			session.getTransaction().commit();
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			session.getTransaction().begin();
			session.persist(emp);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeByIds(List<Integer> list) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(SQLConstants.DELETE_EMP_BY_IDS);
			query.setParameterList("ids", list);
			int i = query.executeUpdate();
			session.getTransaction().commit();
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employee> searchByCriteria(String searchCriteria) {
		// SessionFactory factory = null;
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			Session session = em.unwrap(Session.class);
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Employee.class)
			      .add(Restrictions.disjunction().add(Restrictions.like("firstName", "%" + searchCriteria + "%"))
			            .add(Restrictions.like("lastName", "%" + searchCriteria + "%"))
			            .add(Restrictions.like("email", "%" + searchCriteria + "%")));

			@SuppressWarnings("unchecked")
			List<Employee> empList = criteria.list();
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> searchByEmpId(int searchCriteria) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			Session session = em.unwrap(Session.class);
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employee.class)
			      .add(Restrictions.disjunction().add(Restrictions.eq("id", searchCriteria)));

			@SuppressWarnings("unchecked")
			List<Employee> empList = criteria.list();
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByNameEmail(String firstName, String lastName, String email) {
		try {
//			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//			Session session = em.unwrap(Session.class);
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			if (null != firstName && !firstName.isEmpty()) {
				criteria.add(Restrictions.and(Restrictions.eq("firstName", firstName).ignoreCase()));
			} else if (null != lastName && !lastName.isEmpty()) {
				criteria.add(Restrictions.and(Restrictions.eq("lastName", lastName).ignoreCase()));
			} else if (null != email && !email.isEmpty()) {
				criteria.add(Restrictions.and(Restrictions.eq("email", email).ignoreCase()));
			}

			@SuppressWarnings("unchecked")
			List<Employee> empList = criteria.list();
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}