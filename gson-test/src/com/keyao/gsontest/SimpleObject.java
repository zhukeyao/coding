package com.keyao.gsontest;

import java.util.List;
import java.util.ArrayList;

/**
 * SimpleObject to test Gson 
 * @author keyao
 *
 * */
public class SimpleObject {
	
	private String name;
	private String value;
	private List<String> data;
	
	SimpleObject() {
		name = "age";
		value = "38";
		data = new ArrayList<String>();
		data.add("1");
		data.add("2");		
	}
	
	public static void main() {
		
	}

}
