package com.credera.java.domain.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QueryResult {
	
	private long responseTime;
	private int count;
	private List<Map<String, Collection<Object>>> results;

	public QueryResult() {
		super();
		
		this.responseTime = 0;
		this.count = 0;
		this.results = new ArrayList<Map<String, Collection<Object>>>();
		//this.results = new HashMap<String, String>();
	}

	/**
	 * @return the responseTime
	 */
	public long getResponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the results
	 */
	public List<Map<String, Collection<Object>>> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(List<Map<String, Collection<Object>>> results) {
		this.results = results;
	}
	
	

}
