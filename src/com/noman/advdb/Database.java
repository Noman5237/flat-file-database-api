package com.noman.advdb;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

// FIXME: Implement Flat Serializable
public class Database implements Serializable {
	
	/**
	 *
	 */
	private final String name;
	
	/**
	 *
	 */
	private final HashMap<String, Table> tables;
	
	/**
	 * @param name
	 */
	public Database(String name) {
		this.name = name;
		this.tables = new HashMap<>();
	}
	
	/**
	 * @param databaseName
	 *
	 * @return
	 */
	public static Database importDatabase(String databaseName) {
		String advdbHome = getDatabaseHomeDirectory();
		String filePath = String.format("%s/%s.db", advdbHome, databaseName);
		Database database;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			database = (Database) objectInputStream.readObject();
			return database;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * @param databaseName
	 *
	 * @return
	 */
	public static Database importFlatDatabase(String databaseName) {
		String advdbHome = getDatabaseHomeDirectory();
		String filePath = String.format("%s/%s.txt", advdbHome, databaseName);
		
		Database database;
		try (Scanner scanner = new Scanner(new File(filePath))) {
			database = new Database(databaseName);
			while (scanner.hasNext()) {
				String schemaName = scanner.nextLine();
				String tableName = scanner.nextLine();
				Class<? extends Schema> schema = (Class<? extends Schema>) Class.forName(schemaName);
				Table table = new Table(schema, tableName);
				int noOfRecords = scanner.nextInt();
				// skipping to the next line to read string data
				scanner.nextLine();
				for (int i = 0; i < noOfRecords; i++) {
					String recordFlatString = scanner.nextLine();
					table.addRecord(schema.getDeclaredConstructor(String.class).newInstance(recordFlatString));
				}
				database.addTable(table);
			}
			return database;
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @return
	 */
	private static String getDatabaseHomeDirectory() {
		return System.getenv().get("NOMAN_ADVDB_HOME");
	}
	
	/**
	 * @param table
	 */
	public void addTable(Table table) {
		tables.put(table.getName(), table);
	}
	
	public Table removeTable(String tableName) {
		return tables.remove(tableName);
	}
	
	/**
	 * @param tableName
	 *
	 * @return
	 */
	public Table getTable(String tableName) {
		return tables.get(tableName);
	}
	
	/**
	 *
	 */
	public void export() {
		String advdbHome = getDatabaseHomeDirectory();
		String filePath = String.format("%s/%s.db", advdbHome, this.name);
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			objectOutputStream.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *
	 */
	public void exportFlat() {
		String advdbHome = getDatabaseHomeDirectory();
		String filePath = String.format("%s/%s.txt", advdbHome, this.name);
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
			printWriter.println(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder flatString = new StringBuilder();
		for (Table table : tables.values()) {
			flatString.append(table);
		}
		
		return flatString.toString();
	}
}
