package com.app42.gateway.sample;

import io.swagger.annotations.Api;

import java.util.HashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Api
@Produces(MediaType.APPLICATION_JSON)
public class HttpResponseObject {
	
	private HashMap<String, String> responseHeader;

	public HttpResponseObject(int statusCode, String response, HashMap<String, String> responseHeader) {
		this.statusCode = statusCode;
		this.response = response;
		this.responseHeader = responseHeader;
	}
	
	private int statusCode;
	
	private String response;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public HashMap<String, String> getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(HashMap<String, String> responseHeader) {
		this.responseHeader = responseHeader;
	}
	
	

}
