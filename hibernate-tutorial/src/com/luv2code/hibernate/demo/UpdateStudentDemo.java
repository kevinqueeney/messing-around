package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\nGetting student with id: " + studentId);
			
			Student theStudent = session.get(Student.class, studentId);
			System.out.println("Get complete: " + theStudent);
			
			System.out.println("Updating Student: " + theStudent);
			theStudent.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			sessionFactory.close();
		}
	}

}
