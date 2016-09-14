package com.zsx.web.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.zsx.web.dao.RedisDao;

@Repository
public class RedisDaoImpl implements RedisDao {

	// @Resource(name = "redisTemplate")
	// private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 获取 RedisSerializer
	 */
	protected RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

	@Override
	public boolean add(final String keyStr, final String valueStr) {
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyStr);
				byte[] name = serializer.serialize(valueStr);
				Boolean setNX = connection.setNX(key, name);
				return setNX;
			}
		});
		return result;
	}

	@Override
	public boolean add(final Map<String, String> map) {
		Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				Boolean setNX = false;
				for (Map.Entry<String, String> entry : map.entrySet()) {
					byte[] key = serializer.serialize(entry.getKey());
					byte[] name = serializer.serialize(entry.getValue());
					setNX = connection.setNX(key, name);
				}
				return setNX;
			}
		});
		return result;
	}

	@Override
	public void delete(final String key) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize(key));
				return null;
			}
		});
	}

	@Override
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public boolean update(final String keyStr, final String valueStr) {
		if (get(keyStr) == null) {
			throw new NullPointerException("数据不存在, key = " + keyStr);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyStr);
				byte[] name = serializer.serialize(valueStr);
				connection.set(key, name);
				return true;
			}
		});
		return result;
	}

	@Override
	public String get(final String keyId) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					if (value == null) {
						return null;
					}
					String name = serializer.deserialize(value);
					return name;
				}
				return null;
			}
		});
		return result;
	}

}
