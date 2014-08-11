package com.keyao.gsontest;

import com.keyao.gsontest.SimpleObject;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is a sample to show how to use Gson to convert collection object.
 * @author keyao
 *
 */
public class CollectionObject {

	static public void main(String[] argv) throws Exception {
		
		/**
		 * example of how to convert array to json 
		 */
		int[] intArray = {1, 2, 3, 4, 5};
		Gson gson = new Gson();
		String intArrayJsonOutput = gson.toJson(intArray);
		System.out.println(intArrayJsonOutput);
		System.out.println(gson.toJson(gson.fromJson(intArrayJsonOutput, int[].class)));
		
		/**
		 * example of how to convert arraylist to json
		 */
		List<Integer> intList = Ints.asList(intArray);
		String intListJsonOutput = gson.toJson(intList);
		System.out.println(intListJsonOutput);
		Type intListType = new TypeToken<List<Integer>>(){}.getType();
		System.out.println(gson.toJson(gson.fromJson(intListJsonOutput, intListType)));
				
		/**
		 * example of how to convert Generic collection to json
		 */
		
		Collection<Integer> collectionList = ImmutableList.of(1,2,3,4,5);
		String collectionJsonOutput = gson.toJson(collectionList);
		System.out.println(collectionJsonOutput);
		Type collectionListType = new TypeToken<Collection<Integer>>(){}.getType();
		System.out.println(gson.toJson(gson.fromJson(collectionJsonOutput, collectionListType)));
		
		/**
		 * example of how to convert Generic collection of user-defined object to json
		 */
		Collection<SimpleObject> simpleObjectList = new ArrayList<SimpleObject>();
		SimpleObject obj1 = new SimpleObject();
		SimpleObject obj2 = new SimpleObject();
		simpleObjectList.add(obj1);
		simpleObjectList.add(obj2);
		collectionJsonOutput = gson.toJson(simpleObjectList);
		System.out.println(collectionJsonOutput);
		collectionListType = new TypeToken<Collection<SimpleObject>>(){}.getType();
		System.out.println(gson.toJson(gson.fromJson(collectionJsonOutput, collectionListType)));
			
		
	}
}
