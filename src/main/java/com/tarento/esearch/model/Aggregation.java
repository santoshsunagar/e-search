package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Aggregation {

	@JsonProperty("2")
	public AggregationKey aggregationKey;

	public AggregationKey getAggregationKey() {
		return aggregationKey;
	}

	public void setAggregationKey(AggregationKey aggregationKey) {
		this.aggregationKey = aggregationKey;
	}

	@Override
	public String toString() {
		return "Aggregation [aggregationKey=" + aggregationKey + "]";
	}
	
}
