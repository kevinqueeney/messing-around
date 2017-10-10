package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class EmployeeDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			List<Employee> employees = new ArrayList<Employee>();
			
			// create three employees
			Employee emp1 = new Employee("John", "Doe", "Mastercard");
			employees.add(emp1);
			
			Employee emp2 = new Employee("Jane", "Doe", "Mastercard");
			employees.add(emp2);
			
			Employee emp3 = new Employee("Paul", "Blah", "Google");
			employees.add(emp3);
			
			createEmployees(sessionFactory, employees);
			
			Employee employee = getEmployeeById(sessionFactory, 1);
			System.out.println("Employee with id[1]: " + employee);
			
			List<Employee> mastercardEmployees = getEmployeesByCompany(sessionFactory, "Mastercarrd");
			System.out.println("Mastercard employees:");
			displayEmployees(mastercardEmployees);
			
			System.out.println("Deleting Employee with id[1]");
			deleteEmployee(sessionFactory, employee);
			
			System.out.println("Deleting all Employees");
			deleteAllEmployees(sessionFactory);
			
		} finally {	
			sessionFactory.close();
		}
	}

	private static void createEmployees(SessionFactory sessionFactory, List<Employee> employees) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		for (Employee employee : employees) {
			session.save(employee);
		}		
		session.getTransaction().commit();
	}
	
	private static Employee getEmployeeById(SessionFactory sessionFactory, int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Employee employee = session.get(Employee.class, id);
		
		session.getTransaction().commit();
		
		return employee;
	}
	
	private static List<Employee> getEmployeesByCompany(SessionFactory sessionFactory, String company) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		List<Employee> employees = session.createQuery("from Employee where company=:company", Employee.class)
				.setParameter("company", company)
				.getResultList();
		
		session.getTransaction().commit();
		
		return employees;
	}
	
	private static void displayEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
	
	private static void deleteEmployee(SessionFactory sessionFactory, Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.delete(employee);
		
		session.getTransaction().commit();
	}
	
	private static void deleteAllEmployees(SessionFactory sessionFactory) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.createQuery("delete from Employee").executeUpdate();
		
		session.getTransaction().commit();
	}
}
