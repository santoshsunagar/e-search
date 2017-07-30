package com.tarento.esearch.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AggregationKey {

	@JsonProperty("doc_count_error_upper_bound")
	public String docCountErrorUpperBound;
	
	@JsonProperty("sum_other_doc_count")
	public String sumOtherDocCount;
	
	@JsonProperty("buckets")
	public List<Bucket> buckets;

	public String getDocCountErrorUpperBound() {
		return docCountErrorUpperBound;
	}

	public void setDocCountErrorUpperBound(String docCountErrorUpperBound) {
		this.docCountErrorUpperBound = docCountErrorUpperBound;
	}

	public String getSumOtherDocCount() {
		return sumOtherDocCount;
	}

	public void setSumOtherDocCount(String sumOtherDocCount) {
		this.sumOtherDocCount = sumOtherDocCount;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	@Override
	public String toString() {
		return "AggregationKey [docCountErrorUpperBound="
				+ docCountErrorUpperBound + ", sumOtherDocCount="
				+ sumOtherDocCount + ", buckets=" + buckets + "]";
	}

}
