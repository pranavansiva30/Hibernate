package org.pranavan.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
/*@DiscriminatorValue(value = "fourWheeler")*/
public class FourWheeler extends Vehicle {
private String SteeringWheel;

public String getSteeringWheel() {
	return SteeringWheel;
}

public void setSteeringWheel(String steeringWheel) {
	SteeringWheel = steeringWheel;
}

}
