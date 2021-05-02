package com.noman.advdb.query;

import com.noman.advdb.Schema;

@FunctionalInterface
public interface Validator {
	
	boolean validate(Schema record);
	
}
