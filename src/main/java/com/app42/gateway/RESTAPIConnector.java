/**
 * 
 */
package com.app42.gateway;

import java.io.IOException;
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
	public static String port  = "8081";

	private void sendPost(String uri, String body, String orgName, String orgKey) {
		final DefaultVertx defaultVertx = new DefaultVertx();
		System.out.println("Starting POST....");
		final HttpClient client = defaultVertx.createHttpClient().setHost(host).setPort(new Integer(port));
		final HttpClientRequest cReq = client.request("POST", uri, new Handler<HttpClientResponse>() {
			public void handle(HttpClientResponse cRes) {
				System.out.println("Proxying response: " + cRes.statusCode());

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

		// cReq.headers().set("Content-Length",
		// String.valueOf(Files.size(Paths.get(filePath))));
		cReq.headers().set("orgName", orgName);
		cReq.headers().set("orgKey", orgKey);


        cReq.setChunked(true);
        cReq.write(""+body);
        cReq.end();

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
