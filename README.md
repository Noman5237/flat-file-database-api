Flat file database API in Java with schemas
Check the package app scripts to see how to use this database api

### Create a simple Schema Class which implements Schema interface

```java
public class Customer implements Schema {
	
	private String id;
	private final String name;
	private int age;
	private final CustomerGender gender;
	private int noOfPurchases;
	private CustomerType customerType;

}

```
### Define how to read and write data to Database in the schema
Future: Implement flat serialization methods imposed by schema interface

```java
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

```

### Setup getters and setters method so that query can find the relevant information
```java

// ================================GETTERS==================================== //

public String getId() {
    return id;
}

...

public CustomerType getCustomerType() {
    return customerType;
}


// ================================SETTERS==================================== //

public void setAge(int age) {
    this.age = age;
}

...

public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
}
```

### Setup the environment variable
Setup the environment variable 'NOMAN_ADVDB_HOME' to the directory you want to keep your flat file databases

### Use API to write to database

```java
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
```

### Perform Simple Queries on the tables

```java
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
```

#### Create issues if you have any queries.