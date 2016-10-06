package com.shephertz;

import com.shephertz.app42.paas.customcode.HttpRequestObject;
import com.shephertz.app42.paas.customcode.HttpResponseObject;
import com.shephertz.app42.paas.customcode.PostProcessRuleExecutor;

public class MyPostRule implements PostProcessRuleExecutor{

    @Override
    public void postProcessExecute(HttpRequestObject request, HttpResponseObject response) {
        System.out.println(" ########## Inside Pre Process Rule ######## " + request.getApiKey());
        /*********************
         * 
         * Write your Post Processing Logic
         * 
         **********************/
    }

}