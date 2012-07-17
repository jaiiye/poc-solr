package com.credera.java.dao.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.credera.java.domain.search.Facet;
import com.credera.java.domain.search.FacetRange;
import com.credera.java.domain.search.Query;
import com.credera.java.domain.search.QueryField;

@Repository("searchDao")
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;

	public QueryResponse query(String term, 
			List<QueryField> queryFields) {
		return this.query(term, queryFields, null, null, 0, 0,null); 
	}

	public QueryResponse query(String term, 
			List<QueryField> queryFields,
			int rows,
			int page) {
		return this.query(term, queryFields, null, null, rows, page, null); 
	}

	public QueryResponse query(String term, 
			List<QueryField> queryFields,
			List<String> fields) {
		return this.query(term, queryFields, fields, null, 0, 0, null); 
	}

	public QueryResponse query(String term, 
			List<QueryField> queryFields,
			List<String> fields,
			int rows,
			int page) {
		return this.query(term, queryFields, fields, null, rows, page, null); 
	}

	public QueryResponse query(String term, 
			List<QueryField> queryFields,
			List<String> fields,
			List<Facet> facetFields) {
		return this.query(term, queryFields, fields, facetFields, 0, 0, null); 
	}
	
	public QueryResponse query(Query q) {
		return this.query(q.getTerm(), q.getQueryFields(), q.getFields(), q.getFacetFields(), q.getRows(), q.getPage(), q.getFilter());
	}

	public QueryResponse query(String term, 
			List<QueryField> queryFields,
			List<String> fields,
			List<Facet> facetFields,
			int rows,
			int page,
			String filter) {
		SolrQuery query = new SolrQuery();
		QueryResponse response = null;

		if (term != null && term.length() > 0) {
			query.setQuery(term);
		} else {
			query.setQuery("*");
		}
		
		if (fields != null) {
			for (String field : fields)
				query.addField(field);
		}
		
		if (filter != null) {
			query.setFilterQueries(filter);
		}
		
		if (queryFields != null && queryFields.size() > 0) {
			int pos = 0;
			StringBuilder builder = new StringBuilder();

			for (QueryField queryField : queryFields) {
				if (pos++ > 0)
					builder.append(' ');
				builder.append(queryField.toQuery());
			}
			
			query.set("qf", builder.toString());
		}
		
		if (facetFields != null && facetFields.size() > 0) {
			query.setFacet(true);
			query.setFacetMinCount(1);
			for (Facet field : facetFields) {
				if (field.getRange() == null || field.getRange().size() == 0) {
					query.addFacetField(field.getField());
				} else {
					for (FacetRange range : field.getRange()) {
						query.addFacetQuery(range.toQuery());
					}
				}
			}
		}
		
		query.set("defType", "edismax");
		
		if (rows > 0)
			query.setRows(rows);
		
		if (page > 1)
			query.setStart((query.getRows()*(page-1)));
		
		try {
			response = solrServer.query(query);
			
//		    if (response.getFacetFields() != null) {
//			    for (FacetField field : response.getFacetFields()) {
//			    	System.out.println("field: " + field.getName() + " | " + field.getValueCount());
//			    	System.out.println("");
//			    	for (FacetField.Count count : field.getValues()) {
//			    		System.out.println(" --" + count.getName() + " -> " + count.getCount());
//			    	}
//			    }
//		    }
//		    
//		    if (response.getFacetQuery() != null) {
//			    Map<String,Integer> facetQueries = response.getFacetQuery();
//			    for (String key : facetQueries.keySet()) {
//			    	System.out.println("fq: " + key + " -> " + facetQueries.get(key));
//			    }
//		    }

			
//			if (facetRanges != null && facetRanges.size() > 0 && response.getFacetQuery() != null) {
//				for (FacetRange range : facetRanges) {
//					System.out.println(range.getLabel() + " (" + response.getFacetQuery().get(range.toQuery()) + ")");
//				}
//			}
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}


}
