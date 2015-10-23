/**
 * 
 */
package com.app42.gateway.sample;

import java.util.HashMap;

import javax.ws.rs.Path;

import com.shephertz.app42.paas.customcode.Executor;
import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PostProcessRuleExecutor;
import com.shephertz.app42.paas.customcode.PreProcessRuleExecutor;

/**
 * @author Ajay
 *
 */


@Path("external/info/")
public class MyAPI implements Executor, PreProcessRuleExecutor, PostProcessRuleExecutor {
	@Override
	public HttpResponseObject execute(HttpRequestObject requestObject) {
		// Get Request Body
		String method = requestObject.getMethod();
		String requestBody = requestObject.getBody();
		// Get Header Map
		HashMap<String, String> requestHeaders = null;
		System.out.println(" ############### Executor Called ############### " + method);
		// ########### Write your Logic Below ############//
		// ................................................//
		// ................................................//
		// ................................................//
		// ########### Logic End ############//

		// Send Your Response.....

		HashMap<String, String> responseHeader = new HashMap<String, String>();
		responseHeader.put("MyCustomHeader", "XXXXXXX");

		int responseStatus = 200; // OK...
		String responseMessage = "{'message':'Success/Custom Message'}"; 
		return new HttpResponseObject(responseStatus, responseMessage, responseHeader);
	}

	@Override
	public HttpResponseObject postProcessExecute(HttpRequestObject arg0) {
		// TODO Auto-generated method stub
		System.out.println("########## This is Post flow #############");
		return null;
	}

	@Override
	public HttpResponseObject preProcessExecute(HttpRequestObject arg0) {
		System.out.println("########## This is Pre flow #############");
		return null;
	}

}
