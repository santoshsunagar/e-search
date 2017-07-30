package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hit {

	@JsonProperty("total")
	public Integer total;
	
	@JsonProperty("max_score")
	public Float maxScore;
	
	@JsonProperty("hits")
	public long hits;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Float getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "Hit [total=" + total + ", maxScore=" + maxScore + ", hits="
				+ hits + "]";
	}

}
