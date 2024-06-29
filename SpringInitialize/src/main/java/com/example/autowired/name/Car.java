package com.example.autowired.name;

public class Car {
	private Specification specification; // dependency


	/* public Car(Specification specification) {
	// injected via wiring by constructor, NOT auto-wiring
		this.specification = specification;
	} */

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public void displayDetails() {
		System.out.println("Car Details: " + specification.toString());
	}
}
