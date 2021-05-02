package com.noman.app.scripts;

import com.noman.advdb.Database;
import com.noman.advdb.Table;
import com.noman.app.schemas.simple.customer.Customer;
import com.noman.app.schemas.simple.customer.CustomerGender;
import com.noman.app.schemas.simple.customer.CustomerType;
import com.noman.app.schemas.simple.product.Product;

public class PrepareSimpleDatabases {
	
	public static void main(String[] args) {

//		Creating a new database only for customers
		Database customerDatabase = new Database("Customers");
//		Creating a new table with Customer Schema
		Table basicInfoTable = new Table(Customer.class, "Basic Customer Info.");
//		Linking the table to the database
		customerDatabase.addTable(basicInfoTable);
//		Adding some customers
		basicInfoTable.addRecord(new Customer("1", "Md. Abdullah", 20, CustomerGender.MALE, 0, CustomerType.RARE));
		basicInfoTable.addRecord(new Customer("2", "Emma", 30, CustomerGender.FEMALE, 10, CustomerType.MODERATE));
		basicInfoTable.addRecord(new Customer("4", "Mr. Noman", 40, CustomerGender.MALE, 25, CustomerType.MODERATE));
//		Exporting the customer database to file system
		customerDatabase.exportFlat();

//		Creating a database for products only
		Database productDatabase = new Database("Products");
//		Creating a new table with Product Schema
		Table productTable = new Table(Product.class, "Product Table");
//		Linking the table to the database
		productDatabase.addTable(productTable);
//		Adding some products
		productTable.addRecord(new Product("1", "Lux", 1053.56f, 100.00f));
		productTable.addRecord(new Product("4", "LifeBoy", 23.56f, 100.00f));
		productTable.addRecord(new Product("2", "SavLon", 33.56f, 100.00f));
		productTable.addRecord(new Product("3", "Vim", 1033.00f, 100.00f));
		
//		Exporting the products database
		productDatabase.exportFlat();
		
//		Loading database from filesystem
		Database loadedDatabase = Database.importFlatDatabase("Customers");
		System.out.println(loadedDatabase);
		loadedDatabase = Database.importFlatDatabase("Products");
		System.out.println(loadedDatabase);
		
	}
}
