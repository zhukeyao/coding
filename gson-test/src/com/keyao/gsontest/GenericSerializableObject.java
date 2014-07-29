package com.keyao.gsontest;

import java.lang.reflect.*;

public abstract class GenericSerializableObject<T> implements SerializableObject<T> {
   
    protected Class entityClass;
    
    GenericSerializableObject(Class entityClass) {
    	this.entityClass = entityClass;
    }
}
