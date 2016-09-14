package com.zsx.web.dao;

import java.util.List;
import java.util.Map;

public interface RedisDao {

	/**
	 * 新增
	 * @param key
	 * @param value
	 * @return
	 */
	boolean add(String key, String value);
	
	
	/**
	 * 批量新增
	 * @param map
	 * @return
	 */
	boolean add(Map<String, String> map);
	
	
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
     * @param key
     * @param value
     * @return
     */
    boolean update(String key, String value);  
  
    /** 
     * 通过key获取 
     * @param keyId 
     * @return  
     */  
    String get(String keyId);
	
}
