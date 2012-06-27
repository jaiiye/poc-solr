package com.credera.java.domain.search;

import java.util.List;

public class Query {
	
	private String term;
	private List<QueryField> queryFields;
	private List<String> fields;
	private List<Facet> facetFields;
	private int rows;
	private int page;
	private String filter;
	
	public Query(String term, List<QueryField> queryFields) {
		super();
		this.term = term;
		this.queryFields = queryFields;
		this.fields = null;
		this.facetFields = null;
		this.rows = 0;
		this.page = 0;
		this.filter = null;
	}
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the queryFields
	 */
	public List<QueryField> getQueryFields() {
		return queryFields;
	}
	/**
	 * @param queryFields the queryFields to set
	 */
	public void setQueryFields(List<QueryField> queryFields) {
		this.queryFields = queryFields;
	}
	/**
	 * @return the fields
	 */
	public List<String> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	/**
	 * @return the facetFields
	 */
	public List<Facet> getFacetFields() {
		return facetFields;
	}
	/**
	 * @param facetFields the facetFields to set
	 */
	public void setFacetFields(List<Facet> facetFields) {
		this.facetFields = facetFields;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

}
