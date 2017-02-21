package org.pranavan.hibernate.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pranavan.hibernate.model.Address;
import org.pranavan.hibernate.model.ContactNo;
import org.pranavan.hibernate.model.FourWheeler;
import org.pranavan.hibernate.model.House;
import org.pranavan.hibernate.model.Role;
import org.pranavan.hibernate.model.TwoWheeler;
import org.pranavan.hibernate.model.UserDetails;
import org.pranavan.hibernate.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {

		UserDetails user = new UserDetails();// Transient object
		user.setUserName("First User");
		user.setJoinedDate(new Date());
		user.setDescription("Description of the user goes here");
		UserDetails user2 = new UserDetails();
		user2.setUserName("Second User");
		user2.setJoinedDate(new Date());
		user2.setDescription("Description of the user goes here");
		Address address = new Address();
		address.setStreet("Street Name");
		address.setCity("City Name");
		Address address2 = new Address();
		address2.setStreet("Second Street Name");
		address2.setCity("Second City Name");

		// user.setHomeAddress(address);
		// user.setOfficeAddress(address2);
		ContactNo contactNo = new ContactNo();
		contactNo.setType("Land");
		contactNo.setNumber("0112360762");
		ContactNo contactNo2 = new ContactNo();
		contactNo2.setType("Home");
		contactNo2.setNumber("0779025266");
		Set<ContactNo> contactNoList = new HashSet<ContactNo>();
		contactNoList.add(contactNo);
		contactNoList.add(contactNo2);
		user.setContactNoList(contactNoList);
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");
		user.setVehicle(vehicle);
		FourWheeler car = new FourWheeler();
		car.setVehicleName("Porsche");
		car.setSteeringWheel("Porsche Steering Wheel");
		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("Bike");
		bike.setSteeringHandle("Bike Steering Handle");

		House house = new House();
		house.setHouseName("house1");
		House house2 = new House();
		house2.setHouseName("house2");
		Role role1 = new Role();
		role1.setRolename("ADMIN");
		Role role2 = new Role();
		role2.setRolename("USER");
		// house.setUser(user);
		// house2.setUser(user);
		user.getHouses().add(house);
		user.getHouses().add(house2);
		user.getRoles().add(role1);
		user.getRoles().add(role2);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// session.save(user);
		session.persist(user);// to activate cascade persist
		
		session.save(vehicle);
		session.save(car);
		session.save(bike);
		// cascade operation saving when save user
		/*session.save(house);
		session.save(house2);*/
		
		session.save(role1);
		session.save(role2);
		// session.save(user2);
		session.persist(user2);
		session.getTransaction().commit();
		session.close();

		

	}

}
