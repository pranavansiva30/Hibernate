package org.pranavan.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
private int roleId;
private String rolename;
private Collection<UserDetails> users=new ArrayList<UserDetails>();

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public int getRoleId() {
	return roleId;
}

public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getRolename() {
	return rolename;
}
public void setRolename(String rolename) {
	this.rolename = rolename;
}
@ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
public Collection<UserDetails> getUsers() {
	return users;
}
public void setUsers(Collection<UserDetails> users) {
	this.users = users;
}



}
