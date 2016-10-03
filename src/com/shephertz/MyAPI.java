package com.shephertz;

import java.io.*;
import java.util.HashMap;

import com.shephertz.app42.paas.customcode.Executor;
import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import java.lang.Throwable;

public class MyAPI implements Executor{
	public static void main(String[] args)
	  {
	  }
	@Override
	public HttpResponseObject execute(HttpRequestObject requestObject) {
	    //Get Request Body
	    String requestBody = requestObject.getBody();
	    //Get Header Map
	    HashMap<String, String> requestHeaders = requestObject.getHeaderMap();
	    /*********************************************************************
	     * 
	     * Write your Business Logic
	     * 
	     *
	     *
	     *********************************************************************/
	    
	   // Set Response Body Message
	    
	    String responseMessage  = requestBody+"{\"a\": \"b\",\"c\": \"d\"}"; // You can pass your custom message
	   
	    
	    HashMap<String, String> responseHeader = new HashMap<String, String>();
	    responseHeader.put("MyCustomHeader", "CustHeadValue"+requestHeaders.values());
	    
	    // Set Response Status
	    
	    int responseStatus = 200; // OK...
	    
	    return new HttpResponseObject(responseStatus, responseMessage , responseHeader);
	}

	  

}


