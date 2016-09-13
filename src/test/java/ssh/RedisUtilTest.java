package ssh;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zsx.web.dao.RedisDao;
import com.zsx.web.entity.Tuser;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/spring-hibernate.xml", "file:src/main/resources/springMVC-servlet.xml" })

public class RedisUtilTest {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> template;
	
//	@Autowired
//	private RedisDao<Tuser> redisDaoImpl;
	
	
	@org.springframework.cache.annotation.Cacheable(value = "testId", key = "'id_' + #id")
	public int testCache(int id){
		System.out.println(id);
		return id++;
	}
	
	@Test
	public void test1() {
		System.out.println(template);
		testCache(1);
	}
	
	
	
	@Test
	public void test2() {
		testCache(1);
	}
	
	
	@Test
	public void testAdd() {
//		redisDaoImpl.add(new Tuser());
		
		template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key  = template.getStringSerializer().serialize("asdf");  
                byte[] name = template.getStringSerializer().serialize("zxcvqwer");  
                Boolean setNX = connection.setNX(key, name);
                return setNX; 
			}
			
		});
	}
	
}
