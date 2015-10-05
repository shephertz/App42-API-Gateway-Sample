package com.app42.gateway;


import io.swagger.jaxrs.Reader;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

import com.test.resource.Resource1041;

public class SpecsGenerator {
	
	private  static Reader reader = null;
	
	public static void generate() {
		
	}
	
	public static void main(String[] args) {
		Swagger swaggerObj  = new Swagger();
    	Info info = new Info();
    	info.description("This is My API.. Try it out below Operations...");
    	info.setTitle("My API");
    	swaggerObj.setInfo(info);
    	swaggerObj.setHost("localhost:8090/");
    	
    	reader = new Reader(swaggerObj);
    	Swagger swagger = getSwagger(Resource1041.class);
    	Json.prettyPrint(swagger);
        System.out.println( "Hello World! "  + swagger);
	}
	
	private static Swagger getSwagger(Class<?> resource) {
        return reader.read(resource);
    }

}
