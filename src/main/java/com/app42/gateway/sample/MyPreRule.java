package com.app42.gateway.sample;

import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;
import org.vertx.java.core.http.HttpServerRequest;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PreProcessRuleExecutor;

public class MyPreRule implements PreProcessRuleExecutor{

	@Override
	public void preProcessExecute(HttpRequestObject request, HttpResponseObject response) {
		System.out.println("Body :: >> "+request.getBody());
		
		/*
		 * Applied rules should not throw exception...
		 * like 
		 *     rule ::  request.getHeaderMap().get("convert").equals("XMLTOJSON")
		 *             if "convert" is not in headerMap then it will return null.... and null does not have equals method....so will throw an error.
		 * 	 Condition should be like :: >>  
		 * 		request.getHeaderMap().get("convert")!=null && request.getHeaderMap().get("convert").equals("XMLTOJSON")
		 * */
		
		
		
		/*change body of current request object.*/
		Converter convertor=new Converter();
		JSONObject json=null;
		try {
			json=convertor.convertXmlToJson(request.getBody());
			request.setBody(json.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		Class<HttpRequestObject> cls = (Class<HttpRequestObject>) request.getClass();
	    try {
	    	if(json!=null){
	    		 Field body = cls.getDeclaredField("body");
		    	 body.setAccessible(true);
				 body.set(request, json.toString());
				 System.out.println("request >> "+request.getBody());
	    	}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println(" ########## Inside Pre Process Rule ######## " + request.getApiKey());
		System.out.println("New Body :: >> "+request.getBody());
		
	}

} 
