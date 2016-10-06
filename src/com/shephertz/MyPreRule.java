package com.shephertz.rules;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PreProcessRuleExecutor;

public class MyPreRule implements PreProcessRuleExecutor{

    @Override
    public void preProcessExecute(HttpRequestObject request, HttpResponseObject response) {
        System.out.println(" ########## Inside Pre Process Rule ######## " + request.getApiKey());
        /*********************
         * 
         * Write your Pre Processing Logic
         * 
         **********************/
        //setting response by calling response.setStatusCode, will return response from here 
        //without further processing on your main API and next Pre Process rules.
    }

}