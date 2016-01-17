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
public class UploadHelper {

	public CountDownLatch countDown = new CountDownLatch(1);
	public static String host = "localhost";
	public static String port  = "8089";

	
	
	public void uploadFile(String uri, String filePath, String orgName, String orgKey) {
		final DefaultVertx defaultVertx = new DefaultVertx();
		System.out.println("Starting....");
		final HttpClient client = defaultVertx.createHttpClient().setHost(host).setPort(new Integer(port));
		// final HttpClientRequest cReq = client.request("POST",
		// "/provision/jar/1.0/MyAPI", new Handler<HttpClientResponse>() {
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

		try {
			cReq.headers().set("Content-Length", String.valueOf(Files.size(Paths.get(filePath))));
			cReq.headers().set("orgName", orgName);
			cReq.headers().set("orgKey", orgKey);

			// For a chunked upload you don't need to specify size, just do:
			// cReq.setChunked(true);

			defaultVertx.fileSystem().open(filePath, new AsyncResultHandler<AsyncFile>() {
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
		}

	}

	private void sendPost(String uri, String body, String orgName, String orgKey) {
		final DefaultVertx defaultVertx = new DefaultVertx();
		System.out.println("Starting....");
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

		cReq.end();

	}

	public static void main(String[] args) throws InterruptedException {
		String baseURL = null;
		String filePath = null;
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
		UploadHelper uplaodHandler = new UploadHelper();
		System.out.println("############# Uploading File to : " + baseURL + " : " + filePath + " ###############");
		uplaodHandler.uploadFile(baseURL, filePath, orgName, orgKey);
		uplaodHandler.countDown.await();
		System.out.println("File Uplaoded..");
		
		
	}

}
