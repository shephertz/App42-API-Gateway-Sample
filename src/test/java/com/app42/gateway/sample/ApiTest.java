package com.app42.gateway.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.impl.DefaultVertx;

import com.app42.gateway.RESTAPIConnector;
import com.app42.gateway.UploadHelper;

public class ApiTest {
	
	
	@Test
	public void authentication(){
		String url="/1.0/MyTestAPI";
		String orgName="shephertz";
		String orgKey="123";
		
		
		
	}
	
	
	
	
	/*
	public void createAPITest(){
		
		String baseURL = "/provision/config/1.0/MyTestAPI";
		String filePath =  "config.json";
		String orgName = "shephertz";
		String orgKey = "123";
		
		UploadHelper uplaodHandler = new UploadHelper();
		System.out.println("############# Uploading File to : " + baseURL + " : " + filePath + " ###############");
		uplaodHandler.uploadFile(baseURL, filePath, orgName, orgKey);
		try {
			uplaodHandler.countDown.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Config File Uploaded..");
		
	}*/
	

	public void createIAMTest(){
		String baseURL = "/provision/iam/";
		String filePath =  "iam.json";
		String orgName = "shephertz";
		String orgKey = "123";
		
		/*UploadHelper uplaodHandler = new UploadHelper();
		System.out.println("############# Uploading File to : " + baseURL + " : " + filePath + " ###############");
		uplaodHandler.uploadFile(baseURL, filePath, orgName, orgKey);
		try {
			uplaodHandler.countDown.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("IM File Uploaded..");
		*/
		RESTAPIConnector uplaodHandler = new RESTAPIConnector();
		System.out.println("############# Sending Request to : " + baseURL + " : " + filePath + " ###############");
		uplaodHandler.sendPost(baseURL, filePath, orgName, orgKey);
		System.out.println("Request Posted.....");

	}
	
	
	

}
