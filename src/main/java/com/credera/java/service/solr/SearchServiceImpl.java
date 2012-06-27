package com.credera.java.service.solr;

import java.util.List;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credera.java.dao.solr.SearchDao;
import com.credera.java.domain.search.Facet;
import com.credera.java.domain.search.Query;
import com.credera.java.domain.search.QueryField;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;
	
	public SearchServiceImpl() {}
	
	public QueryResponse query(String term, List<QueryField> queryFields) {
		return searchDao.query(term, queryFields);
	}

	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields) {
		return searchDao.query(term, queryFields, fields);
	}

	public QueryResponse query(String term, List<QueryField> queryFields, int rows, int page) {
		return searchDao.query(term, queryFields, rows, page);
	}

	public QueryResponse query(String term, List<QueryField> queryFields, List<String> fields, int rows, int page) {
		return searchDao.query(term, queryFields, fields, rows, page);
	}

	public QueryResponse query(String term, List<QueryField> queryFields,
			List<String> fields, List<Facet> facetFields) {
		return searchDao.query(term, queryFields, fields, facetFields);
	}
	
	public QueryResponse query(Query q) {
		return searchDao.query(q);
	}

	public QueryResponse query(String term, List<QueryField> queryFields,
			List<String> fields, List<Facet> facetFields, int rows, int page, String filter) {
		return searchDao.query(term, queryFields, fields, facetFields, rows, page, filter);
	}



}
