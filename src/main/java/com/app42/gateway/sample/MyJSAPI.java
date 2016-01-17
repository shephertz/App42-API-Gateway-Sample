/**
 * 
 */
package com.app42.gateway.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.shephertz.app42.paas.customcode.Executor;
import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;


/**
 * @author Ajay
 *
 */
public class MyJSAPI implements Executor {

	@Override
	public HttpResponseObject execute(HttpRequestObject requestObject) {
		//Get Request Body
		String requestBody = requestObject.getBody();
		System.out.println("Request Body " + requestBody);
		
		System.out.println("Request URI " + requestObject.getUri());
		//Get Header Map
		HashMap<String, String> requestHeaders = requestObject.getHeaderMap();
		
		// ########### Write your Logic Below ############//
		//................................................//
		//................................................//
		//................................................//
		// ###########        Logic End       ############//
		
		// Send Your Response.....
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		  // JavaScript code in a String
        //String script = "function hello(name) { print('Hello, ' + name); }";
		String responseMessage  = "{'message':'Success/Custom Message'}";
        String script = "function hello(a,b) { return a + b }";
        try {
        	
			engine.eval(new java.io.FileReader(new File("D:\\work\\dev\\java\\APISample\\test.js")));
		} catch (ScriptException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Invocable inv = (Invocable) engine;
        
       
        // invoke the global function named "hello"
        try {
			Object result  = inv.invokeFunction("requestURL", requestBody,  ""+requestObject.getHeaderMap(), ""+requestObject.getUri());
			responseMessage  = "{'message':"+result+"}"; 
			System.out.println(" Result :  " + result.getClass());
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // evaluate script
       
		
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		responseHeader.put("MyCustomHeader", "XXXXXXX");
		
		int responseStatus = 200; // OK...
		 // You can pass your custom message
		return new HttpResponseObject(responseStatus, responseMessage , responseHeader);
	}
	
	
	 public static void main(String[] args) throws Exception {
	        ScriptEngineManager manager = new ScriptEngineManager();
	        ScriptEngine engine = manager.getEngineByName("JavaScript");

	        // JavaScript code in a String
	        String script = "function hello(name) { print('############### Hello###############, ' + name); }";
	        // evaluate script
	        engine.eval(script);

	        // javax.script.Invocable is an optional interface.
	        // Check whether your script engine implements or not!
	        // Note that the JavaScript engine implements Invocable interface.
	        Invocable inv = (Invocable) engine;

	        // invoke the global function named "hello"
	        inv.invokeFunction("hello", "Scripting!!" );
	    }

}
