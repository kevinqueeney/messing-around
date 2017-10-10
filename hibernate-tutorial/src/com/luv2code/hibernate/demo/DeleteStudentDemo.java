package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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

			System.out.println("Deleting Student:" + theStudent);
			session.delete(theStudent);
			
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			sessionFactory.close();
		}
	}

}
