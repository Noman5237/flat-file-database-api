package com.noman.advdb.query;

import com.noman.advdb.Schema;
import com.noman.advdb.Table;

import java.util.ArrayList;

public enum Query {
	FIND {
		@Override
		protected ArrayList<Schema> operate(ArrayList<Schema> records) {
			ArrayList<Schema> result = new ArrayList<>();
			for (int i = 0, currentLimit = 0; currentLimit < queryLimit && i < records.size(); i++) {
				if (queryValidator.validate(records.get(i))) {
					result.add(records.get(i));
					currentLimit++;
				}
			}
			return result;
		}
	},
	
	UPDATE {
		@Override
		public Query perform(Action action) {
			this.queryAction = action;
			return this;
		}
		
		@Override
		protected ArrayList<Schema> operate(ArrayList<Schema> records) {
			ArrayList<Schema> result = new ArrayList<>();
			for (int i = 0, currentLimit = 0; currentLimit < queryLimit && i < records.size(); i++) {
				Schema record = records.get(i);
				if (queryValidator.validate(record)) {
					queryAction.perform(record);
					currentLimit++;
				}
				
				result.add(record);
			}
			return result;
		}
	};
	
	protected ArrayList<Schema> operate(ArrayList<Schema> records) {
		throw new UnsupportedOperationException("Operation for specified action is not implemented yet");
	}
	
	protected int queryLimit = -1;
	protected String queryName;
	protected Table queryTable;
	protected Validator queryValidator;
	protected Action queryAction;
	
	public Query limit(int queryLimit) {
		this.queryLimit = queryLimit;
		return this;
	}
	
	public Query setQueryName(String queryName) {
		this.queryName = queryName;
		return this;
	}
	
	public Query from(Table table) {
		this.queryTable = table;
		return this;
	}
	
	public Query where(Validator validator) {
		this.queryValidator = validator;
		return this;
	}
	
	public Query perform(Action action) {
		throw new UnsupportedOperationException("Operation for specified action is not supported");
	}
	
	
	/**
	 * FIXME: Operate on table after implementing flat serialization
	 *
	 * @return
	 */
	public Table execute() {
		Table resultsTable = new Table(queryTable.getSchema(), queryName);
		ArrayList<Schema> records = queryTable.getRecordsCopy();
		
		// If query limit is not set the query will execute upto the length of records
		if (queryLimit == -1) {
			queryLimit = records.size();
		}
		
		ArrayList<Schema> newRecords = operate(records);
		for (Schema record : newRecords) {
			resultsTable.addRecord(record);
		}
		return resultsTable;
	}
	
}
