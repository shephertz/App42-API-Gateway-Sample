package com.app42.gateway.sample;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Converter {

	public static Map<String, Object> convertXMLToMap(String xmlIn) {

		// convert to XML
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("map", java.util.Map.class);
		// from XML, convert back to map
		Map<String, Object> map2 = (Map<String, Object>) xStream.fromXML(xmlIn);
		return map2;
	}

	public static Map<String, Object> convertQueryStringToMap(String queryString) throws UnsupportedEncodingException {
		Map<String, Object> map2 = new HashMap<String, Object>();
	    String[] pairs = queryString.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        map2.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
		return map2;
	}

	public static Map<String, Object> convertHeaderMapToMap(Map<String, String> headerMap) throws UnsupportedEncodingException {
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (Map.Entry<String, String> entry : headerMap.entrySet())
		{
		    map2.put(entry.getKey(), entry.getValue());
		}
		return map2;
	}
	
//	public static Map<String, Object> convertJSONToMap(String jsonIn) {
//		return null;
//	}
//
//	public static String convertListToJSON(List<Map<String, Object>> listInput) {
//		return null;
//	}
//
	public static String convertListToXML(List<Map<String, Object>> listInput) {

		// convert to XML
		XStream xStream = new XStream();
		xStream.alias("result", java.util.List.class);
		String xml = xStream.toXML(listInput);
		//System.out.println(xml);
		return xml;
	}

	public static JSONObject convertXmlToJson(String xml)throws JSONException{
		
		JSONObject json=null;
		if(xml!=null && !xml.isEmpty()){
			json=XML.toJSONObject(xml);
		}
		return json;
	}
	
	
	public static String convertJsonToXml(String jsonStr) throws JSONException{
	
		String xml=null;
		if(jsonStr!=null && !jsonStr.isEmpty()){
			JSONObject jsonObj=new JSONObject(jsonStr);
			xml=XML.toString(jsonObj);
		}
		return xml;
	}
	
	
	
	public static List<Object> convertJsonArrayToList(String jsonArr) throws JsonParseException, JsonMappingException, IOException, JSONException{
		
		List<Object> list=null;
		if (jsonArr!=null && !jsonArr.isEmpty()) {
			ObjectMapper mapper=new ObjectMapper();
			JSONArray jsonArray=new JSONArray(jsonArr);
			list = mapper.readValue(jsonArray.toString(), ArrayList.class);
		}
		return list;
	}
	
	
	public static Map<String, Object> convertJsonToMap(String json) throws JsonParseException, JsonMappingException, IOException, JSONException{
		
		Map<String, Object> map=null;
		if (json!=null && !json.isEmpty()) {
			ObjectMapper mapper=new ObjectMapper();
			map=mapper.readValue(new JSONObject(json).toString(), HashMap.class);
		}
		return map;
	}
		public static void main(String[] args) {
		String value = "ajay";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n <map><entry><string>name</string><string>chris</string></entry></map>";
/*
		String query = "select * from service where id = ?";
		List<Map<String, Object>> res = MSDataSource.getInstance().executeQuery(query, true,
				new Object[] { new Long(1) });
		System.out.println(res);
		System.out.println(convertListToXML(res));*/
		System.out.println(convertXMLToMap(xml));
	}

}
