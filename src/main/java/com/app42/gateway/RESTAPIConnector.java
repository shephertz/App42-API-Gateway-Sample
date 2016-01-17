/**
 * 
 */
package com.app42.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.file.AsyncFile;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.impl.DefaultVertx;
import org.vertx.java.core.streams.Pump;

/**
 * @author Ajay
 *
 */
public class RESTAPIConnector {

	CountDownLatch countDown = new CountDownLatch(1);
	public static String host = "localhost";
	public static String port  = "8089";

	public void sendPost(String uri, String body, String orgName, String orgKey) {
		final DefaultVertx defaultVertx = new DefaultVertx();
		System.out.println("Starting POST...."+body);
		final HttpClient client = defaultVertx.createHttpClient().setHost(host).setPort(new Integer(port));
		
		System.out.println("Run client >> ");
		
		final HttpClientRequest cReq = client.request("POST", uri, new Handler<HttpClientResponse>() {
			
			public void handle(HttpClientResponse cRes) {
				System.out.println("inside handler or not ?");
				System.out.println("Proxying response: " + cRes.statusCode());
				System.out.println(cRes.headers().entries());
				cRes.dataHandler(new Handler<Buffer>() {
					public void handle(Buffer data) {
						System.out.println("Proxying response body:" + data);
					}
				});
				cRes.endHandler(new VoidHandler() {
					public void handle() {
						System.out.println(" Response Ended...");

						client.close();
						defaultVertx.stop();
						countDown.countDown();

					}
				});

			}
		});

		
		
		/*try {
			cReq.headers().set("Content-Length", String.valueOf(Files.size(Paths.get(body))));
			cReq.headers().set("orgName", orgName);
			cReq.headers().set("orgKey", orgKey);

			// For a chunked upload you don't need to specify size, just do:
			// cReq.setChunked(true);

			defaultVertx.fileSystem().open(body, new AsyncResultHandler<AsyncFile>() {
				public void handle(AsyncResult<AsyncFile> ar) {
					final AsyncFile file = ar.result();
					Pump pump = Pump.createPump(file, cReq);
					pump.start();

					file.endHandler(new VoidHandler() {
						public void handle() {

							file.close(new AsyncResultHandler<Void>() {
								public void handle(AsyncResult<Void> ar) {
									if (ar.succeeded()) {
										cReq.end();

										System.out.println("Sent request");
									} else {
										ar.cause().printStackTrace(System.err);
									}
								}
							});
						}
					});
				}

				public void onException(Exception e) {
					e.printStackTrace();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*try {
			cReq.headers().set("Content-Length",String.valueOf(Files.size(Paths.get(body))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		cReq.headers().set("orgName", orgName);
		cReq.headers().set("orgKey", orgKey);
		
		
		cReq.setChunked(true);
        cReq.write(""+body);
       // cReq.sendHead();
        cReq.end();

   /*  //http:localhost:8089/provision/iam/
        uri="http:localhost:8089"+uri;
        	  URL url=null;
        	  BufferedReader br=null;
        	  InputStream is=null;
        	  InputStreamReader isr=null;
        	  try {
        	                       //Creating URL object using StringURL.
        	   url = new URL(uri);

        	    //Opening url stream and holding Stream, it returns InputStream object.
        	//  url.openStream();
        	  url.openConnection().connect();
        	 */ 
/*
        	   //Creating InputStreamReader object using returned InputStream to read content.
        	   isr=new InputStreamReader(is);

        	   //Creating BufferedReader object using InputStreamReader. 
        	   br = new BufferedReader(isr);

        	   //Reading returned content from url.
        	   String content;
        	   while ((content= br.readLine()) != null)
        	   System.out.println(content);

        	                        //Closing BufferedReader.
        	   br.close();
*/
		/*	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        
        
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		String baseURL = null;
		String filePath =  null;
		String orgName = null;
		String orgKey = null;
		if (args == null || args.length < 4) {
			throw new RuntimeException("Required Input Arguments are Missing");
		}
		baseURL = args[0];
		filePath = args[1];
		orgName = args[2];
		orgKey = args[3];
		if(args[4]!=null)
			host = args[4];
		if(args[5]!=null)
			port = args[5];
		

		if (baseURL == null) {
			throw new RuntimeException("baseURL can not be null or blank");
		}
		if (filePath == null) {
			throw new RuntimeException("filePath can not be null or blank");
		}
		if (orgName == null || orgKey == null) {
			throw new RuntimeException("orgName/orgKey can not be null or blank");
		}
		String body  = null;
		if(filePath.startsWith("file:////")) {
			filePath = filePath.replaceAll("file:////", "");
			body = new String(Files.readAllBytes(Paths.get(filePath))); 
		}else {
			body = filePath;
		}
		
		RESTAPIConnector uplaodHandler = new RESTAPIConnector();
		System.out.println("############# Sending Request to : " + baseURL + " : " + body + " ###############");
		uplaodHandler.sendPost(baseURL, body, orgName, orgKey);
		uplaodHandler.countDown.await();
		System.out.println("Request Posted.....");

	}
}
