package com.noman.app.schemas.onlineAdmission;

import com.noman.advdb.Schema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Candidate implements Schema {
	
	private String applicationNo;
	private String name;
	private String fatherName;
	private SimpleDateFormat simpleDateFormat;
	private Date dateOfBirth;
	
	{
		simpleDateFormat = new SimpleDateFormat("dd-MMM-yyy");
	}
	
	public Candidate(String applicationNo, String name, String fatherName, Date dateOfBirth) {
		this.applicationNo = applicationNo;
		this.name = name;
		this.fatherName = fatherName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Candidate(String flatString) throws NoSuchMethodException, ParseException {
		try (Scanner scanner = new Scanner(flatString)) {
			scanner.useDelimiter(",");
			this.applicationNo = scanner.next();
			this.name = scanner.next();
			this.fatherName = scanner.next();
			this.dateOfBirth = simpleDateFormat.parse(scanner.next());
		} catch (NoSuchElementException e) {
			throw new NoSuchMethodException(e.getMessage() + " Arguments does not match correct format to instantiate an object of Customer class");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s", applicationNo, name, fatherName, dateOfBirth);
	}
	
	// ================================GETTERS==================================== //
	
	public String getApplicationNo() {
		return applicationNo;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFatherName() {
		return fatherName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
}
