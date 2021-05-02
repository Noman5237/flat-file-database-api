package com.noman.app.scripts;

import com.noman.advdb.Database;
import com.noman.advdb.Table;
import com.noman.advdb.query.Query;
import com.noman.app.schemas.simple.customer.CustomerType;

public class SimpleQuery {
	
	public static void main(String[] args) {
		
		// Just printing the query results not actually updating the database
		// TODO: Implement database table changes after flat serialization is achieved
		// Update to the database file is not done because the action will not be reproducible
		
		Database products = Database.importFlatDatabase("Products");
		if (products != null) {
			Query query = Query.FIND
					.setQueryName("Low Priced Products")
					.from(products.getTable("Product Table"))
					.where(product -> (float) product.get("price") < 1000f);
			
			Table queryResults = query.execute();
			System.out.println(queryResults);
		}
		
		Database customers = Database.importFlatDatabase("Customers");
		if (customers != null) {
			Query query = Query.UPDATE
					.setQueryName("Update Customer Status")
					.from(customers.getTable("Basic Customer Info."))
					.perform(customer -> customer.set("customerType", CustomerType.FREQUENT))
					.where(customer -> (int) customer.get("noOfPurchases") > 20);
			
			Table queryResults = query.execute();
			System.out.println(queryResults);
		}
	}
}
