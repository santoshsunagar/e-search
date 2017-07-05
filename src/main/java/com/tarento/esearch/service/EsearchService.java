package com.tarento.esearch.service;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;

public interface EsearchService {

	/*
	 * This is to create Document
	 */
	public void createDocument(String index, String type, String searchId, String requestData);
	
	/*
	 * This is to update Document
	 */
	public void updateDocument(String index, String type, String searchId, String field, String newValue, String requestData);
	
	/*
	 * This is to remove Document
	 */
	public void removeDocument(String index, String type, String searchId);
	
	/*
	 * This is to get Document
	 */
	public Map<String, Object> getDocument(String index, String type, String searchId);
	
	/*
	 * This is to get All Documents 
	 */
	public List<Map<String,Object>> getAllDocuments(String index);
	
	/*
	 * This is to get Available Documentst
	 */
	public SearchResponse getAvailableDocuments(String index) throws com.tarento.esearch.exception.NoNodeAvailableException ;
	
	
	
}
