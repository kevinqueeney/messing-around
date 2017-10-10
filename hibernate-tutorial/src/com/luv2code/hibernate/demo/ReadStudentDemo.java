package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object");
			String dateOfBirthStr = "16/01/1986";
			Date dateOfBirth = DateUtils.parseDate(dateOfBirthStr);
			Student student = new Student("Daffy", "Duck", dateOfBirth, "daff.duck@kq.kq");
			
			session.beginTransaction();
			
			System.out.println("Saving the student");
			System.out.println(student);
			session.save(student);
			
			System.out.println("Commiting the transaction");
			session.getTransaction().commit();
			
			System.out.println("Saved student. Generated id: " + student.getId());
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\nGetting student with id: " + student.getId());
			
			Student theStudent = session.get(Student.class, student.getId());
			System.out.println("Get complete: " + theStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}

}
