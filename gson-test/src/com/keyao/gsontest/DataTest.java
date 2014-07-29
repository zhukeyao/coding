package com.keyao.gsontest;
import java.lang.reflect.*;

public class DataTest<T> {

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public DataTest() {
    	 Class t0 = getClass();
         System.out.println(t0.toString());
         Type t1 = t0.getGenericSuperclass();
         System.out.println(t1.toString());
         ParameterizedType t2 = (ParameterizedType)t1;
         System.out.println(t2.toString());
         Type[] t3 = t2.getActualTypeArguments();
        Type type = t3[0];
        if (type instanceof Class) {
            this.entityClass = (Class<T>) type;
          } else if (type instanceof ParameterizedType) {
            this.entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
          }
        
        System.out.println(entityClass.toString());
        
    }
}