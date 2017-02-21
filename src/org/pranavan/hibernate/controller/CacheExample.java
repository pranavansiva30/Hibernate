package org.pranavan.hibernate.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pranavan.hibernate.model.UserDetails;

public class CacheExample {

	public static void main(String[] args) {
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		//UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		//UserDetails user2 = (UserDetails) session.get(UserDetails.class, 1);//this query will not re run(reason first level cache)
	    
		Query query=session.createQuery("from UserDetails where userId=:userId");
		query.setParameter("userId", 1);
		query.setCacheable(true);
		UserDetails user4 = (UserDetails)query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		Session session2= sessionFactory.openSession();
	
		session2.beginTransaction();
		
		//UserDetails user3 = (UserDetails) session2.get(UserDetails.class, 1);//this query will not re run(reason second level cache)
		Query query2=session2.createQuery("from UserDetails where userId=:userId");
		query2.setParameter("userId", 1);
		query2.setCacheable(true);
		UserDetails user5 = (UserDetails)query2.uniqueResult();
		session2.getTransaction().commit();
		session2.close();
	
	}

}
