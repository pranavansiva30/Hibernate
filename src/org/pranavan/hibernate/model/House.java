package org.pranavan.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="house_details")
public class House {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int houseId;
	private String houseName;
	//private UserDetails user;
	
	@Column(name="house_id")
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	@Column(name="house_name")
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	/*@ManyToOne
	//@JoinColumn(name="USER_ID")
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
*/	
	

}
