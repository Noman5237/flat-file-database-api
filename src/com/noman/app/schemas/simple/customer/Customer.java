package com.noman.app.schemas.simple.customer;

import com.noman.advdb.Schema;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Customer implements Schema {
	
	private String id;
	private final String name;
	private int age;
	private final CustomerGender gender;
	private int noOfPurchases;
	private CustomerType customerType;
	
	public Customer(String id, String name, int age, CustomerGender gender, int noOfPurchases, CustomerType customerType) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.noOfPurchases = noOfPurchases;
		this.customerType = customerType;
	}
	
	public Customer(String flatString) throws NoSuchMethodException {
		try (Scanner scanner = new Scanner(flatString)) {
			scanner.useDelimiter(",");
			this.id = scanner.next();
			this.name = scanner.next();
			this.age = scanner.nextInt();
			this.gender = CustomerGender.valueOf(scanner.next());
			this.noOfPurchases = scanner.nextInt();
			this.customerType = CustomerType.valueOf(scanner.next());
		} catch (NoSuchElementException e) {
			throw new NoSuchMethodException(e.getMessage() + " Arguments does not match correct format to instantiate an object of Customer class");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%d,%s,%d,%s", id, name, age, gender, noOfPurchases, customerType);
	}
	
	
	// ================================GETTERS==================================== //
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public CustomerGender getGender() {
		return gender;
	}
	
	public int getNoOfPurchases() {
		return noOfPurchases;
	}
	
	public CustomerType getCustomerType() {
		return customerType;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// ================================SETTERS==================================== //
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setNoOfPurchases(int noOfPurchases) {
		this.noOfPurchases = noOfPurchases;
	}
	
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
}
