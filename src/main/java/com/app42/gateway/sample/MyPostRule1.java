package com.app42.gateway.sample;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PostProcessRuleExecutor;

public class MyPostRule1 implements PostProcessRuleExecutor{

	@Override
	public void postProcessExecute(HttpRequestObject arg0, HttpResponseObject arg1) {
		// TODO Auto-generated method stub
		System.out.println("######### From Post Process Executor Class #############");
	}

}
