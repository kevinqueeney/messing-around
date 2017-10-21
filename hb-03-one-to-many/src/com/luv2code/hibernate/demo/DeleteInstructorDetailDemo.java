package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, 4);
			System.out.println("instructorDetail: " + instructorDetail);
			
			System.out.println("the associated instructor: " + instructorDetail.getInstructor());
			
			System.out.println("Deleting instructorDetail: " + instructorDetail);
			Instructor instructor = instructorDetail.getInstructor();
			instructor.setInstructorDetail(null);
			session.delete(instructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
