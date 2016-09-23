package ssh;

import java.io.Serializable;
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
import com.zsx.web.service.UserService;

/**
 * 单元测试
 * @author developer
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)

// 
@WebAppConfiguration(value = "src/main/webapp") //可以不填，默认此目录

@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/spring-hibernate.xml", "file:src/main/resources/springMVC-servlet.xml" })

// 注意下面这种方式已经过时了，不推荐了， 直接去掉即可
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false) // 如果等于true，则每个单元测试都进行事务回滚 
//@Transactional
public class UtilTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void before(){
//		userDao.executeSql("INSERT INTO `user` VALUES ('2231','zhao','shuxue')");
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
	
	
	
	
	@Test
	public void add(){
		Tuser user = new Tuser();
		user.setId("99");
		user.setName("Zsx");
		user.setPwd("pwd");
//		Serializable save = userDao.save(user);
//		System.out.println(save);
//		userDao.saveOrUpdate(user);
		
		userService.addUser(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
