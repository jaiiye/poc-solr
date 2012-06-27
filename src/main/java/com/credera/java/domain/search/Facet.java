package com.credera.java.domain.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facet {
	
	private String field;
	private String label;
	private List<FacetRange> range;
	private Map<String,String> result;
	
	public Facet(String field, String label) {
		super();
		this.field = field;
		this.label = label;
		this.range = new ArrayList<FacetRange>();
		this.result = new HashMap<String,String>();
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the range
	 */
	public List<FacetRange> getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(List<FacetRange> range) {
		this.range = range;
	}

	/**
	 * @return the result
	 */
	public Map<String, String> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Map<String, String> result) {
		this.result = result;
	}
	
	

}
