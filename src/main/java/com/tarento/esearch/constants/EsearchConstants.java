package com.tarento.esearch.constants;

import java.util.HashMap;
import java.util.Map;

public class EsearchConstants {

	
	public static String CLUSTER_NAME = "cluster.name";
	
	public static String CLUSTER_NAME_VALUE = "elasticsearch";
	
	public static String CLUSTER_NODE_HOST = "127.0.0.1";
	
	public static Integer CLUSTER_NODE_PORT = 9300;
	
	public static Map<String, String> JSONTAGS;
	
	public static Integer RATING_COUNT = 6;
	
	public static Integer STATUS_200 = 200;
	
	public static Integer STATUS_201 = 201;
	
	static
    {
		JSONTAGS = new HashMap<String, String>();
		JSONTAGS.put("took", "took");
		JSONTAGS.put("timed_out", "timed_out");
		JSONTAGS.put("total", "total");
		JSONTAGS.put("successful", "successful");
		JSONTAGS.put("failed", "failed");
		JSONTAGS.put("_shards", "_shards");
		JSONTAGS.put("max_score", "max_score");
		JSONTAGS.put("hits", "hits");
		JSONTAGS.put("rate", "rate");
		JSONTAGS.put("key", "key");
		JSONTAGS.put("doc_count", "doc_count");
		JSONTAGS.put("doc_count_error_upper_bound", "doc_count_error_upper_bound");
		JSONTAGS.put("sum_other_doc_count", "sum_other_doc_count");
		JSONTAGS.put("buckets", "buckets");
		JSONTAGS.put("2", "2");
		JSONTAGS.put("aggregations", "aggregations");
		JSONTAGS.put("status", "status");
    }
	
}
