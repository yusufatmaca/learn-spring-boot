package com.example.autowired.constructor;

public class Car {
	private Specification specification; // dependency

	public Car(Specification specification) {
		this.specification = specification;
	}

	/*
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	 */

	public void displayDetails() {
		System.out.println("Car Details: " + specification.toString());
	}
}
