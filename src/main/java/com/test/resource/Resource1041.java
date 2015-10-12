package com.test.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.app42.gateway.sample.HttpResponseObject;



@Api(value = "/external/info/", authorizations = @Authorization("my_auth"))
@Path("external/info/")
public class Resource1041 {
    @ApiOperation(value = "API1")
    @GET
    @Path("/path1")
    @Produces({"application/json", "application/xml"})
    public void getTest(@HeaderParam(value = "name") ArrayList<String> tenantId) {
        return;
    }

    @ApiOperation(value = "API2", authorizations = @Authorization("your_auth"))
    @GET
    @Path("/path2")
    public void getTest2(@HeaderParam(value = "name") ArrayList<String> tenantId) {
        return;
    }
    
    @ApiOperation(value = "API4")
    @GET
    @Path("/path3")
    @Produces({"application/json", "application/xml"})
    public HttpResponseObject execute() {
    	return null;
    }

}