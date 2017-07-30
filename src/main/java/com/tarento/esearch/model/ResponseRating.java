package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "took", "timed_out", "_shards", "hits", "aggregations", "status" })
public class ResponseRating {

	@JsonProperty("took")
	public long took;
	
	@JsonProperty("timed_out")
	public boolean timedOut;
	
	@JsonProperty("_shards")
	public Shard shards;
	
	@JsonProperty("hits")
	public Hit hits;
	
	@JsonProperty("aggregations")
	public Aggregation aggregations;

	@JsonProperty("status")
	public Integer status;

	public long getTook() {
		return took;
	}

	public void setTook(long took) {
		this.took = took;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	public Shard getShards() {
		return shards;
	}

	public void setShards(Shard shards) {
		this.shards = shards;
	}

	public Hit getHits() {
		return hits;
	}

	public void setHits(Hit hits) {
		this.hits = hits;
	}

	public Aggregation getAggregations() {
		return aggregations;
	}

	public void setAggregations(Aggregation aggregations) {
		this.aggregations = aggregations;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResponseRating [took=" + took + ", timedOut=" + timedOut
				+ ", shards=" + shards + ", hits=" + hits + ", aggregations="
				+ aggregations + ", status=" + status + "]";
	}

}
