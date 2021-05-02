package com.noman.app.scripts;

import com.noman.advdb.Database;

public class PrepareAdvancedDatabases {
	
	public static void main(String[] args) {
		
//		Import the candidates and their marks
		Database candidates = Database.importFlatDatabase("Candidates");
		Database candidateMarks = Database.importFlatDatabase("Candidates Marks");
		
		if (candidates != null && candidateMarks != null) {
//			Create a new database that contains both tables
			Database onlineAdmissionDatabase = new Database("Online Admission Database");
			onlineAdmissionDatabase.addTable(candidates.getTable("Candidates"));
			onlineAdmissionDatabase.addTable(candidateMarks.getTable("Candidate Marks"));
			System.out.println(onlineAdmissionDatabase);
//			Export the big database
			onlineAdmissionDatabase.exportFlat();
		}
		
	}
}
