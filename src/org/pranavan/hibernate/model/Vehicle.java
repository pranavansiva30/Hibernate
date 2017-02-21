package org.pranavan.hibernate.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy=InheritanceType.JOINED)
/*@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)*/
/*@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VEHICLE_TYPE",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value = "vehicle")*/
@Table(name="vehicle")
public class Vehicle {

private int vehicleId;
private String vehicleName;
@Id
//@GeneratedValue(strategy=GenerationType.AUTO)
@GeneratedValue(strategy = GenerationType.TABLE)
@Column(name="vehicle_id")
public int getVehicleId() {
	return vehicleId;
}
public void setVehicleId(int vehicleId) {
	this.vehicleId = vehicleId;
}
@Column(name="vehicle_name")
public String getVehicleName() {
	return vehicleName;
}
public void setVehicleName(String vehicleName) {
	this.vehicleName = vehicleName;
}


}
