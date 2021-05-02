package com.noman.advdb.query;

import com.noman.advdb.Schema;

@FunctionalInterface
public interface Action {
	
	void perform(Schema record);
	
}
