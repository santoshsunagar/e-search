package com.tarento.esearch.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseFeedback {

	@JsonProperty("response")
	public ResponseRating responseData;

	public ResponseRating getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseRating responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "ResponseFeedback [responseData=" + responseData + "]";
	}

}
