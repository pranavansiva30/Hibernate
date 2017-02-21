package org.pranavan.hibernate.controller;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pranavan.hibernate.model.UserDetails;

public class HibernateCrud {
	public static void main(String[] args) {
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		UserDetails user3 = new UserDetails(); 
		user3.setUserName("Fifth User");
		user3.setJoinedDate(new Date());
		user3.setDescription("Description of the user goes here");
		//Here 'user3' is TRANSIENT object
		//Long id = (Long) session.save(user3);
		//UserDetails saveuser3 = (UserDetails) session.merge(user3);
		// here user3 is now in a persistent state
		session.getTransaction().commit();
		session.close();
		//Here 'user3' is DETACHED object
		session= sessionFactory.openSession();
		session.beginTransaction();
		user3 = (UserDetails) session.get(UserDetails.class, 1);
		//Here 'user3' is Persistent object

		/* session.update(user3); */
		// session.delete(user3);
		session.getTransaction().commit();
		session.close();
		//Here 'user3' is DETACHED object
		System.out.println("User Name retrieved is " + user3.getUserName());
		user3.setUserName("updated username");
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		//session.update(user3);
		UserDetails saveuser3 = (UserDetails) session.merge(user3);
		// Here 'user3' is Persistent object
		user3.setUserName("updated username1");
		session.getTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
		session.beginTransaction();
		user3 = (UserDetails) session.get(UserDetails.class, 1);
		// Here 'user3' is Persistent object														 
		user3.setUserName("updated username2");
		session.getTransaction().commit();
		session.close();
		// Here 'user3' is Detached object
		System.out.println("User Name retrieved is " + user3.getUserName());
																			
		System.out.println(user3.getContactNoList().size());	
		
		
		
		
	}
}
