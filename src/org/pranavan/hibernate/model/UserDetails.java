package org.pranavan.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name = "UserDetails.byId", query = "from UserDetails where userId=:userId")
@NamedNativeQuery(name = "UserDetails.byName", query = "select * from USER_DETAILS where USER_NAME=:username",resultClass=UserDetails.class)
//@org.hibernate.annotations.Entity(selectBeforeUpdate=true) if object has changes only update query will run
@Table(name="USER_DETAILS")
public class UserDetails {
	
	private int userId;
	private String userName;
	private Date joinedDate;
	private String description;
	/*@Embedded
	@AttributeOverrides({@AttributeOverride(name="street",column=@Column(name="HOME_STREET_NAME")),
		@AttributeOverride(name="city",column=@Column(name="HOME_CITY_NAME")),
		@AttributeOverride(name="state",column=@Column(name="HOME_STATE_NAME")),
		@AttributeOverride(name="pincode",column=@Column(name="HOME_PIN_CODE"))
		
		
	})
	private Address homeAddress;
	@Embedded
	private Address officeAddress;*/
	
	private Collection<ContactNo>contactNoList=new ArrayList<ContactNo>();
	private Collection<House>houses=new ArrayList<House>();
	private Collection<Role> roles=new ArrayList<Role>();
	private Vehicle vehicle;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="USER_NAME")
	//@Transient will not create column for this field in db table
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	@Lob
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ElementCollection(fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@CollectionTable(name="USER_CONTACTNO", joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name="CONTACTNO_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
	public Collection<ContactNo> getContactNoList() {
		return contactNoList;
	}
	public void setContactNoList(Collection<ContactNo> contactNoList) {
		this.contactNoList = contactNoList;
	}
/*	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}*/
	@OneToOne
	@JoinColumn(name="VEHICLE_ID")
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/*@OneToMany(fetch=FetchType.LAZY,mappedBy="user")*/
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="USER_HOUSE",joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns=@JoinColumn(name="HOUSE_ID"))
	public Collection<House> getHouses() {
		return houses;
	}
	public void setHouses(Collection<House> houses) {
		this.houses = houses;
	}
	@ManyToMany
	@JoinTable(name="USER_ROLE",joinColumns=@JoinColumn(name="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
   
	
	
	
	


}
