package com.keyao.gsontest;

import java.lang.reflect.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keyao.gsontest.SerializableObject;

/**
 * Generic class to hold object need to be serialized/deserizlied to json format
 * @author keyao
 *
 * @param <T>  
 */
public class JsonSerializableObject<T> extends GenericSerializableObject<T> {
    
    private T data;
    boolean prettyPrint;
    
    public JsonSerializableObject(T d, Class entityClass) {
    	super(entityClass);
        data = d;
        prettyPrint = false;    
     }
    
    public JsonSerializableObject(Class entityClass) {
        super(entityClass);
    	data = null;
        prettyPrint = false;
    }
    
    @Override
    public String serialize() {
        if (data == null)
            return "";
        
        GsonBuilder builder = new GsonBuilder();
        if (prettyPrint) {
            builder.setPrettyPrinting();
        }
        Gson gson = builder.create();
        return gson.toJson(data);
    }
    
    @Override
    public T deserialize(String input) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        data = (T)gson.fromJson(input, entityClass);
        return data;
    }
    
    public T getData() {
        return data;
    }
    
    public void SetPrettyPrint(boolean flag) {
        prettyPrint = flag;
    }
     
}
