package com.credera.java.dao.solr;

import java.util.List;

import org.apache.solr.client.solrj.response.QueryResponse;

import com.credera.java.domain.search.Facet;
import com.credera.java.domain.search.Query;
import com.credera.java.domain.search.QueryField;

public interface SearchDao {

	public QueryResponse query(String term, List<QueryField> queryFields);
	public QueryResponse query(String term, List<QueryField> queryFields, int rows, int page);
	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields);
	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields, int rows, int page);
	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields, List<Facet> facetFields);
	public QueryResponse query(Query q);
	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields, List<Facet> facetFields, int rows, int page, String filter);

}

