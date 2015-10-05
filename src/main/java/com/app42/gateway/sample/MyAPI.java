/**
 * 
 */
package com.app42.gateway.sample;

import java.util.HashMap;

import com.shephertz.app42.paas.customcode.Executor;
import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;

/**
 * @author Ajay
 *
 */
public class MyAPI implements Executor {

	@Override
	public HttpResponseObject execute(HttpRequestObject requestObject) {
		//Get Request Body
		String requestBody = requestObject.getBody();
		//Get Header Map
		HashMap<String, String> requestHeaders = requestObject.getHeaderMap();
		System.out.println(" ############### Executor Called ###############");
		// ########### Write your Logic Below ############//
		//................................................//
		//................................................//
		//................................................//
		// ###########        Logic End       ############//
		
		// Send Your Response.....
		
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		responseHeader.put("MyCustomHeader", "XXXXXXX");
		
		int responseStatus = 200; // OK...
		String responseMessage  = "{'message':'Success/Custom Message'}"; // You can pass your custom message
		return new HttpResponseObject(responseStatus, responseMessage , responseHeader);
	}

}
