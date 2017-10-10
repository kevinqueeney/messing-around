package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			System.out.println("Creating 3 student objects");
			String dateOfBirthStr = "16/01/1986";
			Date dateOfBirth = DateUtils.parseDate(dateOfBirthStr);
			
			Student student1 = new Student("John", "Doe", dateOfBirth, "john.q@kq.kq");
			Student student2 = new Student("Mary", "Public", dateOfBirth, "mary.q@kq.kq");
			Student student3 = new Student("Bonita", "Applebum", dateOfBirth, "bonita.q@kq.kq");
			
			session.beginTransaction();
			
			System.out.println("Saving the students");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			System.out.println("Commiting the transaction");
			session.getTransaction().commit();
			
			System.out.println("Done");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
}
