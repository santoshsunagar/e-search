package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Bucket {

	@JsonProperty("key")
	public Integer key;
	
	@JsonProperty("response")
	public Integer doc_count;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Integer getDoc_count() {
		return doc_count;
	}

	public void setDoc_count(Integer doc_count) {
		this.doc_count = doc_count;
	}

	@Override
	public String toString() {
		return "Bucket [key=" + key + ", doc_count=" + doc_count + "]";
	}

}
