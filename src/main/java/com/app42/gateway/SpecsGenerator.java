package com.app42.gateway;


import io.swagger.jaxrs.Reader;
import io.swagger.models.Info;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.util.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app42.gateway.sample.MyAPI;
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
    	
    	Map<String,Path> paths  = new HashMap<String, Path>();
    	
    	Operation ops  = new Operation();
    	List<String> consumeList = new ArrayList<String>();
    	List<String> produceList = new ArrayList<String>();
    	List<String> tags = new ArrayList<String>();
    	List<Parameter> parameterList = new ArrayList<Parameter>();
    	BodyParameter param = new BodyParameter();
    	
    	param.setName("testparam");
    	parameterList.add(param );
    	consumeList.add("application/json");
    	produceList.add("application/json");
    	ops.setConsumes(consumeList);
    	ops.setProduces(produceList);
    	ops.setDescription("This is Test Method...");
    	ops.setParameters(parameterList);
    	tags.add("API3");
    	ops.setTags(tags);
    	ops.setSummary("API3");
    	Path p1 = new Path();
    	p1.setPost(ops);
 
    	paths.put("/abc", p1 );
    	swaggerObj.setPaths(paths);

    	reader = new Reader(swaggerObj);
    	Swagger swagger  = reader.read(Resource1041.class);

    	Json.prettyPrint(swagger);
        System.out.println( "Hello World! "  + swagger);
	}
	
	private static Swagger getSwagger(Class<?> resource) {
        return reader.read(resource);
    }

}
