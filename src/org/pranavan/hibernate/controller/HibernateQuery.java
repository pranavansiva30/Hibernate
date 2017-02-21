package org.pranavan.hibernate.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pranavan.hibernate.model.UserDetails;

public class HibernateQuery {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from UserDetails where userId>:userId");//Query query=session.createQuery("from UserDetails where userId>2");
		query.setParameter("userId", 2);
		//Query query=session.createQuery("from UserDetails");
		query.setFirstResult(2);
		query.setMaxResults(2);
		List<UserDetails> users=(List<UserDetails>)query.list();
		session.getTransaction().commit();
		session.close();
		for(UserDetails u:users){
			
			System.out.println(u.getUserName());
		}
		
	    session = sessionFactory.openSession();
		session.beginTransaction();
		query=session.createQuery("select userName from UserDetails");
		query.setFirstResult(2);
		query.setMaxResults(2);
		List<String> userNames=(List<String>)query.list();
		session.getTransaction().commit();
		session.close();
		for(String u:userNames){
			
			System.out.println(u);
		}
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		//Query query=session.createQuery("from UserDetails where userId>2");
		query=session.createQuery("select new map(userId as uid,userName as uname,joinedDate as joinedDate) from UserDetails");
		query.setFirstResult(2);
		query.setMaxResults(2);
		List<Map<String,String>> userDetalmap=(List<Map<String,String>>)query.list();
		session.getTransaction().commit();
		session.close();
		for (Map<String, String> map : userDetalmap) {
			  for (Map.Entry u : map.entrySet()) {
			
			System.out.println(u.getKey()+"-"+u.getValue());
		}}
	}

}
