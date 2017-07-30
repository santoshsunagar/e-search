package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Shard {

	@JsonProperty("total")
	public Integer total;
	
	@JsonProperty("successful")
	public Integer successful;
	
	@JsonProperty("response")
	public Integer failed;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSuccessful() {
		return successful;
	}

	public void setSuccessful(Integer successful) {
		this.successful = successful;
	}

	public Integer getFailed() {
		return failed;
	}

	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	@Override
	public String toString() {
		return "Shard [total=" + total + ", successful=" + successful
				+ ", failed=" + failed + "]";
	}

}
