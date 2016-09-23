package ssh;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zsx.web.dao.RedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/spring-hibernate.xml", "file:src/main/resources/springMVC-servlet.xml" })

public class JedisUtilTest {

	@Resource(name = "jedisPool")
	private JedisPool jedisPool;
	
	private Jedis jedis = null;
	
	@Before
	public void before(){
		jedis = jedisPool.getResource();
	}
	
	@After
	public void after(){
		jedis.close();
	}
	
	@Test
	public void set() {
		String set = jedis.set("zzzzzz", "hahah_asfasfsdf_12313123");
		System.out.println(set); // OK
	}
	
	
	@Test
	public void get() {
		System.out.println(jedis.get("nihao"));
	}
	
	@Test
	public void 是否存在() {
		Boolean exists = jedis.exists("zsx");
		System.out.println(exists); // true
	}
	
	@Test
	public void 删除() {
		Long del = jedis.del("sanme");
		System.out.println(del); // 1
	}
	
	@Test
	public void 更新() {
//		jedis.
//		System.out.println(redisDaoImpl.update("aaa:2", "!@#$%^&"));
	}
	
	@Test
	public void 获取() {
//		System.out.println(redisDaoImpl.get("age"));
	}
}
