package com.noman.app.schemas.onlineAdmission;

import com.noman.advdb.Schema;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CandidateMarks implements Schema {
	
	private String applicationNo;
	private float highSchoolGPA;
	private int physicsMarks;
	private int chemistryMarks;
	private int biologyMarks;
	
	public CandidateMarks(String applicationNo, float highSchoolGPA, int physicsMarks, int chemistryMarks, int biologyMarks) {
		this.applicationNo = applicationNo;
		this.highSchoolGPA = highSchoolGPA;
		this.physicsMarks = physicsMarks;
		this.chemistryMarks = chemistryMarks;
		this.biologyMarks = biologyMarks;
	}
	
	public CandidateMarks(String flatString) throws NoSuchMethodException {
		try (Scanner scanner = new Scanner(flatString)) {
			scanner.useDelimiter(",");
			this.applicationNo = scanner.next();
			this.highSchoolGPA = scanner.nextFloat();
			this.physicsMarks = scanner.nextInt();
			this.chemistryMarks = scanner.nextInt();
			this.biologyMarks = scanner.nextInt();
		} catch (NoSuchElementException e) {
			throw new NoSuchMethodException(e.getMessage() + " Arguments does not match correct format to instantiate an object of Customer class");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s,%.2f,%d,%d,%d", applicationNo, highSchoolGPA, physicsMarks, chemistryMarks, biologyMarks);
	}
	
	// ================================GETTERS==================================== //
	
	public String getApplicationNo() {
		return applicationNo;
	}
	
	public float getHighSchoolGPA() {
		return highSchoolGPA;
	}
	
	public int getPhysicsMarks() {
		return physicsMarks;
	}
	
	public int getChemistryMarks() {
		return chemistryMarks;
	}
	
	public int getBiologyMarks() {
		return biologyMarks;
	}
	
}
