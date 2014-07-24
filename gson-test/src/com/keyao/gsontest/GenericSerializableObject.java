package com.keyao.gsontest;

import java.lang.reflect.*;

public abstract class GenericSerializableObject<T> implements SerializableObject<T> {
   
    protected Class<T> typeParameterClass;
    
    @SuppressWarnings("unchecked")
    GenericSerializableObject() {
        Class t0 = getClass();
        Type t1 = t0.getGenericSuperclass();
        ParameterizedType t2 = (ParameterizedType)t1;
        Type[] t3 = t2.getActualTypeArguments();
        this.typeParameterClass = (Class<T>)(t3[0]);
   //     this.typeParameterClass = ((Class)(t2.getActualTypeArguments()[0]);
    }
}
