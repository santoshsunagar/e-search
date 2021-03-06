package com.tarento.esearch.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tarento.esearch.constants.EsearchConstants;
import com.tarento.esearch.model.Aggregation;
import com.tarento.esearch.model.AggregationKey;
import com.tarento.esearch.model.Bucket;
import com.tarento.esearch.model.Hit;
import com.tarento.esearch.model.ResponseRating;
import com.tarento.esearch.model.Shard;


public class EsearchUtils {

	public static Map<String, Object> prepareESJson(Map<String, String> mapObj) throws Exception {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		try {
			for (Map.Entry<String, String> entry : mapObj.entrySet()) {
			    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			    jsonDocument.put(entry.getKey(), entry.getValue());
			}
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}
		return jsonDocument;
	}
	
	public static JSONObject prepareRatingJson(List<Map<String,Object>> esData, SearchResponse response) throws Exception {
		JSONObject ratingJson = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONObject item = new JSONObject();
		try {
			ratingJson.put(EsearchConstants.JSONTAGS.get("took"), 4);
			ratingJson.put(EsearchConstants.JSONTAGS.get("timed_out"), 0);
			//Prepare _shards
			item.put(EsearchConstants.JSONTAGS.get("total"), response.getSuccessfulShards() + response.getFailedShards());
			item.put(EsearchConstants.JSONTAGS.get("successful"), response.getSuccessfulShards());
			item.put(EsearchConstants.JSONTAGS.get("failed"), response.getFailedShards());
			array.put(item);
			ratingJson.put(EsearchConstants.JSONTAGS.get("_shards"), array);
			
			//Prepare hits
			array = new JSONArray();
			item = new JSONObject();
			item.put(EsearchConstants.JSONTAGS.get("total"), esData.size());
			item.put(EsearchConstants.JSONTAGS.get("max_score"), response.getHits().maxScore());
			item.put(EsearchConstants.JSONTAGS.get("hits"), response.getHits().totalHits);
			array.put(item);
			ratingJson.put(EsearchConstants.JSONTAGS.get("hits"), array);
			
			//Prepare aggregations
			array = new JSONArray();
			Map<String, Integer> rateMap = new HashMap<String, Integer>();
			
			if(null != esData && esData.size() > 0) {
				for (Map<String, Object> map : esData) {
					item = new JSONObject();
					int rate = (int) map.get(EsearchConstants.JSONTAGS.get("rate"));
					
					switch (rate) {
					case 0:
						if (!rateMap.containsKey("0")) {
							rateMap.put("0", 1);
						} else {
							rateMap.put("0", rateMap.get("0") + 1);
						}
						break;
					case 1:
						if (!rateMap.containsKey("1")) {
							rateMap.put("1", 1);
						} else {
							rateMap.put("1", rateMap.get("1") + 1);
						}
						break;
					case 2:
						if (!rateMap.containsKey("2")) {
							rateMap.put("2", 1);
						} else {
							rateMap.put("2", rateMap.get("2") + 1);
						}
						break;
					case 3:
						if (!rateMap.containsKey("3")) {
							rateMap.put("3", 1);
						} else {
							rateMap.put("3", rateMap.get("3") + 1);
						}
						break;
					case 4:
						if (!rateMap.containsKey("4")) {
							rateMap.put("4", 1);
						} else {
							rateMap.put("4", rateMap.get("4") + 1);
						}
						break;
					case 5:
						if (!rateMap.containsKey("5")) {
							rateMap.put("5", 1);
						} else {
							rateMap.put("5", rateMap.get("5") + 1);
						}
						break;

					default:
						break;
					}
				}
			}
			for (int i = 0; i < EsearchConstants.RATING_COUNT; i++) {
				int docCount = 0;
				item = new JSONObject();
				item.put(EsearchConstants.JSONTAGS.get("key"), String.valueOf(i));
				if(rateMap.containsKey(String.valueOf(i))) {
					docCount = rateMap.get(String.valueOf(i));
				}
				item.put(EsearchConstants.JSONTAGS.get("doc_count"), docCount);
				array.put(item);
			}
			item = new JSONObject();
			item.put(EsearchConstants.JSONTAGS.get("doc_count_error_upper_bound"), "0");
			item.put(EsearchConstants.JSONTAGS.get("sum_other_doc_count"), "0");
			item.put(EsearchConstants.JSONTAGS.get("buckets"), array);
			array1.put(item);
			item = new JSONObject();
			item.put(EsearchConstants.JSONTAGS.get("2"), array1);
			
			
			/*item.put("2", new JSONObject().put("doc_count_error_upper_bound", "0"));
			item.put("2", array1);*/
			ratingJson.put(EsearchConstants.JSONTAGS.get("aggregations"), item);
			ratingJson.put(EsearchConstants.JSONTAGS.get("status"), EsearchConstants.STATUS_200);
			
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}
		return ratingJson;
	}
	
	public static ResponseRating prepareRatingResponse(List<Map<String,Object>> esData, SearchResponse response) throws Exception {
		ResponseRating ratingJson = new ResponseRating();
		try {
			Shard shard = new Shard();
			Hit hit = new Hit();
			Aggregation aggregation = new Aggregation();
			AggregationKey aggregationKey = new AggregationKey();
			List<Bucket> buckets = new ArrayList<Bucket>();
			Bucket bucket = new Bucket();
			
			//Prepare response
			ratingJson.setTook(response.getTook().getMillis());
			ratingJson.setTimedOut(false);
			
			//Prepare shards
			shard.setTotal(response.getSuccessfulShards() + response.getFailedShards());
			shard.setSuccessful(response.getSuccessfulShards());
			shard.setFailed(response.getFailedShards());
			ratingJson.setShards(shard);
			
			//Prepare hits
			hit.setTotal(esData.size());
			hit.setMaxScore(response.getHits().maxScore());
			hit.setHits(response.getHits().totalHits);
			ratingJson.setHits(hit);
			
			//Prepare aggregation
			
			aggregationKey.setDocCountErrorUpperBound("0");
			aggregationKey.setSumOtherDocCount("0");
			
			Map<String, Integer> rateMap = new HashMap<String, Integer>();
			if(null != esData && esData.size() > 0) {
				for (Map<String, Object> map : esData) {
					int rate = (int) map.get(EsearchConstants.JSONTAGS.get("rate"));
					
					switch (rate) {
					case 0:
						if (!rateMap.containsKey("0")) {
							rateMap.put("0", 1);
						} else {
							rateMap.put("0", rateMap.get("0") + 1);
						}
						break;
					case 1:
						if (!rateMap.containsKey("1")) {
							rateMap.put("1", 1);
						} else {
							rateMap.put("1", rateMap.get("1") + 1);
						}
						break;
					case 2:
						if (!rateMap.containsKey("2")) {
							rateMap.put("2", 1);
						} else {
							rateMap.put("2", rateMap.get("2") + 1);
						}
						break;
					case 3:
						if (!rateMap.containsKey("3")) {
							rateMap.put("3", 1);
						} else {
							rateMap.put("3", rateMap.get("3") + 1);
						}
						break;
					case 4:
						if (!rateMap.containsKey("4")) {
							rateMap.put("4", 1);
						} else {
							rateMap.put("4", rateMap.get("4") + 1);
						}
						break;
					case 5:
						if (!rateMap.containsKey("5")) {
							rateMap.put("5", 1);
						} else {
							rateMap.put("5", rateMap.get("5") + 1);
						}
						break;

					default:
						break;
					}
				}
			}
			for (int i = 0; i < EsearchConstants.RATING_COUNT; i++) {
				int docCount = 0;
				Integer key = i;
				bucket = new Bucket();
				bucket.setKey(key);
				if(rateMap.containsKey(String.valueOf(i))) {
					docCount = rateMap.get(String.valueOf(i));
				}
				bucket.setDoc_count(docCount);
				buckets.add(bucket);
			}
			aggregationKey.setBuckets(buckets);
			aggregation.setAggregationKey(aggregationKey);
			ratingJson.setAggregations(aggregation);
			
			ratingJson.setStatus(EsearchConstants.STATUS_200);
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}
		return ratingJson;
	}
	
	public static List<Map<String, Object>> getAllDocuments(SearchResponse response) {
		List<Map<String, Object>> esData = null;
		try {
			esData = new ArrayList<Map<String, Object>>();
			for (SearchHit hit : response.getHits()) {
				esData.add(hit.getSource());
			}
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		System.out.println("esData:"+esData);
		return esData;
	}
	
}
