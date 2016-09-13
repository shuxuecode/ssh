package com.zsx.web.dao;

import java.util.List;

public interface RedisDao<T> {

	/**
	 * 新增
	 * @param t
	 * @return
	 */
	boolean add(T t);
	
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	boolean add(List<T> list);
	
	
	/** 
     * 删除 
     * @param key 
     */  
    void delete(String key);  
      
    /** 
     * 删除多个 
     * @param keys 
     */  
    void delete(List<String> keys);  
      
    /** 
     * 修改 
     * @param t 
     * @return  
     */  
    boolean update(T t);  
  
    /** 
     * 通过key获取 
     * @param keyId 
     * @return  
     */  
    T get(String key);
	
}
