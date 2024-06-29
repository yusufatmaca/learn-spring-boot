package car.example.constructor.injection;

public class Car {
	private Specification specification; // dependency

	public Car(Specification specification) {
	// injected via constructor
		this.specification = specification;
	}

	public void displayDetails() {
		System.out.println("Car Details: " + specification.toString());
	}
}
