package com.app42.gateway.sample;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PreProcessRuleExecutor;

public class MyPreRule1 implements PreProcessRuleExecutor{

	@Override
	public HttpResponseObject preProcessExecute(HttpRequestObject arg0) {
		System.out.println(" ########## Inside Pre Process Rule 1 ######## " + arg0.getApiKey());
		return new HttpResponseObject(200, "Returning From Pre Rule 1 Filter...", null);
	}

}
