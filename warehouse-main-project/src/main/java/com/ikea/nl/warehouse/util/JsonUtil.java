package com.ikea.nl.warehouse.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A json utility class
 */
public class JsonUtil {

    /**
     * Hide the constructor
     */
    private JsonUtil() {
    }

    /**
     * A simple method to convert a valid json string to a Collection
     * 
     * @param <T>       Class Type
     * @param json      the Json String
     * @param beanClass the Type
     * @return collection of referenced objects
     * @throws JsonMappingException incase of invalid mapping
     */
    public static final <T> T fromJsonToObject(String json, Class<T> beanClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        if (beanClass != null && json != null) {
            obj = mapper.readValue(json, beanClass);
        }

        return obj;
    }

    /**
     * A simple method to convert a valid json string to a Collection
     * 
     * @param <T>           Class Type
     * @param json          the Json String
     * @param typeReference the Type Refernce
     * @return collection of referenced objects
     * @throws JsonMappingException incase of invalid mapping
     */
    public static final <T> T fromJsonToCollection(String json, TypeReference<T> typeReference)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        if (typeReference != null && json != null) {
            obj = mapper.readValue(json, typeReference);
        }

        return obj;
    }

}
