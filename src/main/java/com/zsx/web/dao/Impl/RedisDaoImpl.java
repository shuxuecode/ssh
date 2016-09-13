package com.zsx.web.dao.Impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.zsx.web.dao.RedisDao;

@Repository
public class RedisDaoImpl<T> implements RedisDao<T> {
	
//	@Autowired
	@Resource(name = "redisTemplate")
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	/** 
     * 获取 RedisSerializer 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    } 

	@Override
	public boolean add(T t) {
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
//				byte[] key  = serializer.serialize(user.getId());  
//                byte[] name = serializer.serialize(user.getName());  
//                return connection.setNX(key, name); 
                byte[] key  = serializer.serialize("asdf");  
                byte[] name = serializer.serialize("1234");  
                Boolean setNX = connection.setNX(key, name);
                return setNX; 
			}
			
		});
		return result;
	}

	@Override
	public boolean add(List<T> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Object>() {  
	        public Object doInRedis(RedisConnection connection) {  
	            connection.del(redisTemplate.getStringSerializer().serialize(""));  
	            return null;  
	        }  
	    }); 
	}

	@Override
	public void delete(List<String> keys) {
//		redisTemplate.delete(keys);
		redisTemplate.delete((Serializable) keys);
	}

	@Override
	public boolean update(T t) {
		/*
		 * String key = user.getId();  
        if (get(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        } 
		 */
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
//                byte[] key  = serializer.serialize(user.getId());  
//                byte[] name = serializer.serialize(user.getName()); 
                byte[] key  = serializer.serialize("");  
                byte[] name = serializer.serialize("");
                connection.set(key, name);  
                return true;  
            }  
        }); 
		return false;
	}

	@Override
	public T get(final String keyId) {
		redisTemplate.execute(new RedisCallback<T>() {

			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(keyId);  
                if (connection.exists(key)) {
                	byte[] value = connection.get(key);  
                    if (value == null) {  
                        return null;  
                    }  
                    String name = serializer.deserialize(value); 
                    return (T) name;
				}
                
				return null;
			}
			
		});
		return null;
	}

}
