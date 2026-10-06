package newProj2024;

public class Car extends Vehicle {
	String engineType;
	
	public Car(String engineType, String brand, String date, String owner, double price){
		super(brand, date, owner, price);
		this.engineType=engineType;
	}
}
