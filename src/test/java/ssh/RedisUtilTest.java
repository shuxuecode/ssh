package ssh;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zsx.web.dao.RedisDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/spring-hibernate.xml", "file:src/main/resources/springMVC-servlet.xml" })

public class RedisUtilTest {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> template;
	
	@Autowired
	private RedisDao redisDaoImpl;
	
	
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
		Boolean hasKey = template.hasKey("zsx");
		System.out.println(hasKey);
		testCache(1);
	}
	
	
	@Test
	public void testAdd() {
		System.out.println(redisDaoImpl.add("nihao", "我很好"));
	}
	
	@Test
	public void 批量更新() {
		Map<String, String> map = new HashMap<>();
		map.put("aaa:1", "abcd");
		map.put("aaa:2", "efg");
		map.put("aaa:3", "hijklmn");
		System.out.println(redisDaoImpl.add(map));
	}
	
	@Test
	public void 删除() {
		redisDaoImpl.delete("asd");
	}
	
	@Test
	public void 更新() {
		System.out.println(redisDaoImpl.update("aaa:2", "!@#$%^&"));
	}
	
	@Test
	public void 获取() {
		System.out.println(redisDaoImpl.get("age"));
	}
}
