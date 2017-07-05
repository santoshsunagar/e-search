package com.tarento.esearch.service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tarento.esearch.constants.EsearchConstants;

public class EsearchServiceImpl implements EsearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EsearchServiceImpl.class);
	
	public void createDocument(String index, String type, String searchId, String requestData){
		LOGGER.info("Start: createDocument service");
		TransportClient transportClient = null;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			//mapObj = EsearchMockUtil.prepareESJson();
			transportClient.prepareIndex(index, type, searchId)
            .setSource(requestData).execute().actionGet();
		} catch (Exception expObj) {
			LOGGER.error("Error in creating Document", expObj);
			expObj.printStackTrace();
		}  finally {
            transportClient.close();
        }
		LOGGER.debug("End: createDocument service");
	}
	
	public void updateDocument(String index, String type, String searchId, String field, String newValue, String requestData) {
		LOGGER.debug("Start: updateDocument service");
		TransportClient transportClient = null;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			Map<String, Object> updateObject = new HashMap<String, Object>();
	        updateObject.put(field, newValue);
	        UpdateRequest updateRequest = new UpdateRequest(index, type, searchId).doc(requestData);
	        transportClient.update(updateRequest).get();
		} catch (Exception expObj) {
			LOGGER.error("Error in updating Document", expObj);
			expObj.printStackTrace();
		} finally {
            transportClient.close();
        }
		LOGGER.info("End: updateDocument service");
	}
	
	public void removeDocument(String index, String type, String searchId) {
		LOGGER.info("Start: removeDocument service");
		TransportClient transportClient = null;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			DeleteResponse response = transportClient.prepareDelete(index, type, searchId).execute().actionGet();
		} catch (Exception expObj) {
			LOGGER.error("Error in removing Document", expObj);
			expObj.printStackTrace();
		} finally {
            transportClient.close();
        }
		LOGGER.info("End: removeDocument service");
	}
	
	public Map<String, Object> getDocument(String index, String type, String searchId) {
		LOGGER.info("Start: getDocument service");
		TransportClient transportClient = null;
		Map<String, Object> source = null;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			GetResponse getResponse = transportClient
					.prepareGet(index, type, searchId).execute().actionGet();
			source= new HashMap<String, Object>();
			source = getResponse.getSource();
		} catch (Exception expObj) {
			LOGGER.error("Error in get Document", expObj);
			expObj.printStackTrace();
		} finally {
            transportClient.close();
        }
		LOGGER.info("End: getDocument service");
		return source;
	}

	public List<Map<String,Object>> getAllDocuments(String index) {
		LOGGER.info("Start: getAllDocuments service");
		TransportClient transportClient = null;
		List<Map<String,Object>> esData = null;
		SearchResponse response = null;
		int scrollSize = 1000;
		int i = 0;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			esData = new ArrayList<Map<String,Object>>();
			while( response == null || response.getHits().hits().length != 0){
	            response = transportClient.prepareSearch("feedback-d1")
	                       .setQuery(QueryBuilders.matchAllQuery())
	                       .setSize(scrollSize)
	                       .setFrom(i * scrollSize)
	                    .execute()
	                    .actionGet();
	            for(SearchHit hit : response.getHits()){
	                esData.add(hit.getSource());
	            }
	            i++;
	        }
		} catch (Exception expObj) {
			LOGGER.error("Error in getAll Document", expObj);
			expObj.printStackTrace();
		} finally {
            transportClient.close();
        }
		LOGGER.info("End: getAllDocuments service");
		return esData;
	}
	
	public SearchResponse getAvailableDocuments(String index) throws com.tarento.esearch.exception.NoNodeAvailableException {
		LOGGER.info("Start: getAvailableDocuments service");
		TransportClient transportClient = null;
		List<Map<String,Object>> esData = null;
		SearchResponse response = null;
		SearchRequestBuilder requestBuilder = null;
		int scrollSize = 1000;
		int i = 0;
		try {
			Settings clusterSettings = Settings.builder().put(EsearchConstants.CLUSTER_NAME, EsearchConstants.CLUSTER_NAME_VALUE).build();
			transportClient = new PreBuiltTransportClient(clusterSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsearchConstants.CLUSTER_NODE_HOST), EsearchConstants.CLUSTER_NODE_PORT));
			/*esData = new ArrayList<Map<String,Object>>();
			while( response == null || response.getHits().hits().length != 0){
				response = transportClient.prepareSearch(index)
	                       .setQuery(QueryBuilders.matchAllQuery())
	                       .setSize(scrollSize)
	                       .setFrom(i * scrollSize)
	                    .execute()
	                    .actionGet();
	            //System.out.println("response:"+response);
	            for(SearchHit hit : response.getHits()){
	                esData.add(hit.getSource());
	            }
	            i++;
	        }*/
			
			//Added
			esData = new ArrayList<Map<String,Object>>();
			requestBuilder = transportClient.prepareSearch(index).setQuery(QueryBuilders.matchAllQuery());
			response = requestBuilder.get();
		} catch (NoNodeAvailableException expObj) {
			throw new com.tarento.esearch.exception.NoNodeAvailableException("NoNodeAvailableException"); 
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
            transportClient.close();
        }
		LOGGER.info("End: getAvailableDocuments service");
		return response;
	}
	
}
