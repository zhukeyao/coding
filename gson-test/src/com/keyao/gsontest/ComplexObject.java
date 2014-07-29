package com.keyao.gsontest;

import java.util.List;
import java.util.ArrayList;

import com.keyao.gsontest.SimpleObject;

public class ComplexObject {
	
	private SimpleObject simpleObject;
	
	private List<SimpleObject> simpleObjectList;
	
	public ComplexObject() {
		this.simpleObject = new SimpleObject();
		
		this.simpleObjectList=new ArrayList<SimpleObject>();
		SimpleObject obj = new SimpleObject();
		SimpleObject obj2 = new SimpleObject();
		
		this.simpleObjectList.add(obj);
		this.simpleObjectList.add(obj2);
	}

	static public void main(String[ ] args ) throws Exception {
		ComplexObject obj = new ComplexObject();
		JsonSerializableObject<ComplexObject> jsonObject = new JsonSerializableObject<ComplexObject>(obj, ComplexObject.class);
		jsonObject.SetPrettyPrint(true);
		String outputString = jsonObject.serialize();
        System.out.println(outputString);
        
        JsonSerializableObject<ComplexObject> jsonObject2 = new JsonSerializableObject(ComplexObject.class);
        jsonObject2.deserialize(outputString);
        ComplexObject testObject2 = jsonObject2.getData();
        System.out.println(testObject2.toString());
        System.out.println(jsonObject2.serialize());
	}
	
}
