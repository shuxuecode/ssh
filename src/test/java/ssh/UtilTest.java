package ssh;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zsx.web.dao.UserDao;
import com.zsx.web.entity.Tuser;

/**
 * 单元测试
 * @author developer
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration(locations = { "file:src/main/resources/spring.xml", "file:src/main/resources/spring-hibernate.xml", "file:src/main/resources/springMVC-servlet.xml" })

@Transactional
public class UtilTest {
	
	@Autowired
	private UserDao userDao;
	
	@Before
	public void before(){
		Tuser user = new Tuser();
		user.setId("2222");
		user.setName("Zsx");
		user.setPwd("pwd");
		userDao.save(user);
		
//		userDao.executeSql("INSERT INTO `user` VALUES ('221','zhao','shuxue')");
		System.out.println("准备测试！！！");
	}
	
	@After
	public void After(){
		System.out.println("测试结束！！！");
	}
	
	@Test
	public void get() {
		Tuser tuser = userDao.get("from Tuser");
		System.out.println(JSON.toJSONString(tuser));
	}
	
//	@Test
//	public void getAll(){
//		List<Tuser> list = userDao.find("from Tuser");
//		for (Tuser tuser : list) {
//			System.out.println(JSON.toJSONString(tuser));
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
