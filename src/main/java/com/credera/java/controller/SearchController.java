package com.credera.java.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.credera.java.data.csv.RawProduct;
import com.credera.java.domain.Product;
import com.credera.java.domain.search.Facet;
import com.credera.java.domain.search.FacetNumericRange;
import com.credera.java.domain.search.FacetRange;
import com.credera.java.domain.search.Query;
import com.credera.java.domain.search.QueryField;
import com.credera.java.service.ProductService;
import com.credera.java.service.solr.SearchService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SearchController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private SolrServer solrServer;

	private @Value("#{configProperties['solr.query.facet.field']}") String[] facetFields;
	private @Value("#{configProperties['solr.query.facet.query']}") String[] facetQueries;

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(value="q", required=false) String term, @RequestParam(value="fq", required=false) String filter, @RequestParam(value="pfq", required=false) String previousFilter, Locale locale, Model model) {
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		SolrQuery query = new SolrQuery();
		query.setQuery(term);
		
		model.addAttribute("solr_query", query );
		myModel.put("solr_query", query);

		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		myModel.put("serverTime", formattedDate);
		
		List <QueryField> queryFields = new ArrayList<QueryField>();
		queryFields.add(new QueryField("description", 10.0));
		queryFields.add(new QueryField("name", 0.25));
		
		List <String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("description");
		fields.add("name");

		List<Facet> facets = new ArrayList<Facet>();
		facets.add(new Facet("manu_exact", "Manufacturer"));
		facets.add(new Facet("webCond_s", "Condition"));
		
		Facet prices = new Facet("price", "Price");
		List<FacetRange> facetRanges = new ArrayList<FacetRange>();
		facetRanges.add(new FacetNumericRange("price", 0.0, 100.0, "$0 - $100"));
		facetRanges.add(new FacetNumericRange("price", 100.0, 500.0, "$100 - $500"));
		facetRanges.add(new FacetNumericRange("price", 500.0, 1000.0, "$500 - $1000"));
		facetRanges.add(new FacetNumericRange("price", 1000.0, 2500.0, "$1000 - $2500"));
		facetRanges.add(new FacetNumericRange("price", 2500.0, Double.MAX_VALUE, ">$2500"));
		prices.setRange(facetRanges);
		facets.add(prices);
		
		Query q = new Query(term, queryFields);
		q.setFields(fields);
		q.setFacetFields(facets);
		q.setRows(10);
		q.setPage(1); // have to set rows first, better way?
		if (previousFilter != null && previousFilter.length() > 0)
			q.setFilter(filter + " AND " + previousFilter);
		else
			q.setFilter(filter);
		
		QueryResponse response = searchService.query(q);
		
		model.addAttribute("qtime", response.getQTime() );
		myModel.put("qtime", response.getQTime());
		model.addAttribute("query_response", response );
		myModel.put("query_response", response);
		model.addAttribute("query_string", query.getQuery());
		model.addAttribute("filter_query", filter);

			System.out.println("response: " + response);
			System.out.println("qtime: " + response.getQTime());
		
		
		return new ModelAndView("home", myModel);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String savePayCode(@ModelAttribute("solr_query") SolrQuery query, BindingResult result, Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List <QueryField> queryFields = new ArrayList<QueryField>();
		queryFields.add(new QueryField("description", 10.0));
		queryFields.add(new QueryField("name", 0.25));
		
		List <String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("description");
		fields.add("name");

		List<Facet> facets = new ArrayList<Facet>();
		facets.add(new Facet("manu_exact", "Manufacturer"));
		facets.add(new Facet("webCond_s", "Condition"));
		
		Facet prices = new Facet("price", "Price");
		List<FacetRange> facetRanges = new ArrayList<FacetRange>();
		facetRanges.add(new FacetNumericRange("price", 0.0, 100.0, "$0 - $100"));
		facetRanges.add(new FacetNumericRange("price", 100.0, 500.0, "$100 - $500"));
		facetRanges.add(new FacetNumericRange("price", 500.0, 1000.0, "$500 - $1000"));
		facetRanges.add(new FacetNumericRange("price", 1000.0, 2500.0, "$1000 - $2500"));
		facetRanges.add(new FacetNumericRange("price", 2500.0, Double.MAX_VALUE, ">$2500"));
		prices.setRange(facetRanges);
		facets.add(prices);
		
		Query q = new Query(query.getQuery(), queryFields);
		q.setFields(fields);
		q.setFacetFields(facets);
		q.setRows(10);
		q.setPage(1); // have to set rows first, better way?
		
		QueryResponse response = searchService.query(q);
		
		model.addAttribute("qtime", response.getQTime() );
		model.addAttribute("query_response", response );
		model.addAttribute("num_found", response.getResults().getNumFound() );
		model.addAttribute("query_string", query.getQuery());
		model.addAttribute("filter_query", "");

			System.out.println("response: " + response);
			System.out.println("qtime: " + response.getQTime());
			

		return "home";
	}
	

	
}