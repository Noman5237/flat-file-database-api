package com.noman.advdb;

import java.io.Serializable;
import java.util.ArrayList;

// FIXME: Implement Flat Serializable
public class Table implements Serializable {
	
	/**
	 * Name of the table
	 */
	private String name;
	
	/**
	 * Schema type of the table
	 * This must be a valid Java Class which implements {@link Schema}
	 */
	private final Class<? extends Schema> schema;
	
	/**
	 * List of records in the table which are all predefined implementation of {@link Schema}
	 */
	private ArrayList<Schema> records;
	
	
	/**
	 * Creates a table provided the schema class and a table name
	 *
	 * @param schema class of schema which implements {@link Schema}
	 * @param name   name of the table
	 */
	public Table(Class<? extends Schema> schema, String name) {
		this.name = name;
		records = new ArrayList<>();
		this.schema = schema;
	}
	
	/**
	 * Inserts a new record to the table
	 *
	 * @param record add a record of predefined schema class
	 */
	public void addRecord(Schema record) {
		records.add(record);
	}
	
	/**
	 * Flat file representation of table which includes <br>
	 * <li>fully qualified schema class name</li>
	 * <li>name of the table</li>
	 * <li>number of records in the table</li>
	 * <li>string representations records of the table defined by the schema of this table</li>
	 *
	 * @return flat file representation of table
	 */
	@Override
	public String toString() {
		StringBuilder flatString = new StringBuilder();
		flatString
				.append(schema.getName())
				.append("\n")
				.append(name)
				.append("\n")
				.append(records.size())
				.append("\n");
		
		for (Schema record : records) {
			flatString.append(record).append("\n");
		}
		return flatString.toString();
	}
	
	/**
	 * @return name of the table
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return schema class of this table
	 */
	public Class<? extends Schema> getSchema() {
		return schema;
	}
	
	/**
	 * FIXME: serialize and deserialize the table and produce a deep copy
	 *
	 * @return
	 */
	public ArrayList<Schema> getRecordsCopy() {
		return records;
	}
}
