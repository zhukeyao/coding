package com.keyao.gsontest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.keyao.gsontest.JsonSerializableObject;

/**
 * SimpleObject to test Gson
 * @author keyao
 *
 * */
public class SimpleObject {
	/**
	 * Test String field
	 */
	private String name;
	/**
	 * Test Integer field
	 */
	private Integer intValue;
	
	/**
	 * Test double field
	 */
	private Double doubleValue;

	/**
	 * Test boolean field
	 */
	private boolean booleanFlag;
	
	/**
	 * Test ArrayList field 
	 */
	private List<String> myList;
	
	
	/**
	 * Test HashMap field
	 */
	private Map<String, String> myMap;
	
	SimpleObject() {
		name = "age";
		intValue = new Integer(28);
		doubleValue = new Double(100.25);
		booleanFlag = true;
		myList = new ArrayList<String>();
		myList.add("list_item1");
		myList.add("list_item2");
		myMap = new HashMap<String, String>();
		myMap.put("mapKey1", "mapValue1");
	}
		
	public static void main( String[ ] args ) throws Exception {
		//DataTest<SimpleObject> testData = new DataTest<SimpleObject>();
		
		SimpleObject testObject = new SimpleObject();
		JsonSerializableObject<SimpleObject> jsonObject = new JsonSerializableObject<SimpleObject>(testObject, SimpleObject.class);
		jsonObject.SetPrettyPrint(true);
		String outputString = jsonObject.serialize();
        System.out.println(outputString);
        
        JsonSerializableObject<SimpleObject> jsonObject2 = new JsonSerializableObject<SimpleObject>(SimpleObject.class);
        jsonObject2.deserialize(outputString);
        SimpleObject testObject2 = jsonObject2.getData();
        System.out.println(testObject2.toString());
        System.out.println(jsonObject2.serialize());
	}

}
