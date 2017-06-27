package com.tarento.esearch.model;

import java.io.Serializable;

public class ResponseStatus implements Serializable {

	public Integer status;
	
	public String statusMessage;

	public ResponseStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseStatus(Integer status, String statusMessage) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
