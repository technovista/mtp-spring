package com.array.testprep.springservice;

import java.util.Map;

/**
 * 
 * @author mtshah
 * 
 * @param <T>
 */
public interface Service<T> {
	public void processRequest(String action, String entity, Object entityObj) throws Exception;

	public Map<String, Object> getModelMap();

	public Object findById(Integer id) throws Exception;

	public boolean isValid(T obj);
}
