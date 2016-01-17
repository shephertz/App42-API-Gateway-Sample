package com.app42.gateway.sample;

import org.json.JSONException;
import org.json.JSONObject;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PostProcessRuleExecutor;

public class MyPostRule1 implements PostProcessRuleExecutor{

	@Override
	public void postProcessExecute(HttpRequestObject request, HttpResponseObject response) {
		
		System.out.println("      ############## Executing MyPostRule1 ###############      ");
		String resStr=response.getResponse();
		
		System.out.println("Response before conversion >> "+resStr);
		
		/* Change Response data..*/
		Converter convertor=new Converter();
		String xml=null;
		try {
			xml=convertor.convertJsonToXml(resStr);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		if(xml!=null){
			response.setResponse(xml.toString());
		}
		
		System.out.println("Response After conversion >> "+response.getResponse());
		System.out.println("######### From Post Process Executor Class #############");
	}

}
