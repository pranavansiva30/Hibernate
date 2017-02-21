package org.pranavan.hibernate.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pranavan.hibernate.model.UserDetails;

public class CriteriaQuery {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(UserDetails.class);
		/*criteria.add(Restrictions.like("userName","%Second User%"))
		.add(Restrictions.between("userId",1,2));*/
		Criterion ls=Restrictions.like("userName","%Second User%");
		Criterion rs=Restrictions.between("userId",1,2);
		LogicalExpression logic=Restrictions.or(ls, rs);
		criteria.add(logic);
		
		/*criteria.add(Restrictions.eq("userName","Second User"))
        .add(Restrictions.gt("userId", 1));*/
			List<UserDetails> users=(List<UserDetails>)criteria.list();
		session.getTransaction().commit();
		session.close();
		for(UserDetails u:users){
			
			System.out.println(u.getUserName());
		}
		//System.out.println("projection functions...");
		//projectionFunctions();
		projectionByexample();
	}
	public static void projectionFunctions(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(UserDetails.class);
		criteria.setProjection(Projections.property("userName"));
		criteria.addOrder(Order.desc("userId"));
		
			List<String> users=(List<String>)criteria.list();
		session.getTransaction().commit();
		session.close();
		for(String u:users){
			
			System.out.println(u);
		}	
		
	}
	public static void projectionByexample(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails exampleUser=new UserDetails();
		exampleUser.setUserName("Second User");
		Example example=Example.create(exampleUser);
		Criteria criteria=session.createCriteria(UserDetails.class);
		criteria.add(example);
		
		
			List<UserDetails> users=(List<UserDetails>)criteria.list();
		session.getTransaction().commit();
		session.close();
		for(UserDetails u:users){
			
			System.out.println(u.getUserName());
		}	
		
	}
}
