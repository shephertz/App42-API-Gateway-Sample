package com.app42.gateway.sample;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PreProcessRuleExecutor;

public class MyPreRule1 implements PreProcessRuleExecutor{

	@Override
	public void preProcessExecute(HttpRequestObject arg0, HttpResponseObject arg1) {
		System.out.println(" ########## Inside Pre Process Rule 1 ######## " + arg0.getApiKey());
		
	}

}
