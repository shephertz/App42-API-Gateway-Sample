function requestURL(body, reqHeader, uri) {
	println(" Request Body ... " + body);
	println(" Request URI ... " + uri);
	println(" Request Header ... " + reqHeader);
	//Get Header Map
	
	var resultArrived = false; 
	var vertx = com.shephertz.app42.gateway.GatewayServer.defaultVertx;
	var body = "";
	var client = vertx.createHttpClient();
	client = client.setHost("s3.amazonaws.com");
	var request = client.get("/cdn.shephertz.com/indextest123.html", function(resp) {
		resp.dataHandler(function(buffer) {
		// Add chunk to the buffer
			body =""+buffer;
			resultArrived = true;
		});

		resp.endHandler(function() {
		// The entire response body has been received
		println(" Println .......");
	});
	
});
request.end();
while(!resultArrived);
return body;
}


function requestURL1() {    
var queryParam = new java.util.Hashtable();
		var headerParams = new java.util.Hashtable();
		var con = com.shephertz.app42.paas.sdk.java.connection.RESTConnectorAsync.getInstance();
		var response = con.executeGet("http://s3.amazonaws.com//cdn.shephertz.com/indextest123.html", queryParam, headerParams);
		println(" ############# " + response);
		
}
