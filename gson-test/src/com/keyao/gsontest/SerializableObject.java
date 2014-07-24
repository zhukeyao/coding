package com.keyao.gsontest;

/**
 * Generic SerializableObject interface
 * @author keyao
 *
 * @param <T> target object class
 */
public interface SerializableObject<T> {
  
    String serialize();
    T deserialize(String input);
}
