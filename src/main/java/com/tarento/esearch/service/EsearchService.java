package com.tarento.esearch.service;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;

public interface EsearchService {

	public void createDocument(String index, String type, String searchId, String requestData);
	
	public void updateDocument(String index, String type, String searchId, String field, String newValue, String requestData);
	
	public void removeDocument(String index, String type, String searchId);
	
	public Map<String, Object> getDocument(String index, String type, String searchId);
	
	public List<Map<String,Object>> getAllDocuments(String index);
	
	public SearchResponse getAvailableDocuments(String index);
	
	
	
}
