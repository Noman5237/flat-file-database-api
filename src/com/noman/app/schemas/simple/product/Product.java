package com.noman.app.schemas.simple.product;

import com.noman.advdb.Schema;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Product implements Schema {
	
	private String id;
	private String name;
	private float price;
	private float weight;
	
	public Product(String id, String name, float price, float weight) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weight = weight;
	}
	
	public Product(String flatString) throws NoSuchMethodException {
		try (Scanner scanner = new Scanner(flatString)) {
			scanner.useDelimiter(",");
			this.id = scanner.next();
			this.name = scanner.next();
			this.price = scanner.nextFloat();
			this.weight = scanner.nextFloat();
		} catch (NoSuchElementException e) {
			throw new NoSuchMethodException(e.getMessage() + " Arguments does not match correct format to instantiate an object of Customer class");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%.2f,%.2f", id, name, price, weight);
	}
	
	// ================================GETTERS==================================== //
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public float getWeight() {
		return weight;
	}
	
	
	// ================================SETTERS==================================== //
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
