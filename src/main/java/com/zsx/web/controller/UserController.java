package com.zsx.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.zsx.web.entity.Tuser;
import com.zsx.web.service.UserService;
/**
 * 用户
 * @author zsx
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 跳转到用户管理模块
	 */
	@RequestMapping("toUserManager")
	public String toUserManager(){
		return "jsp/user/userManager";
	}
	/**
	 * 添加用户
	 */
//	@RequestMapping("/addUser")
//	@ResponseBody
//	public void addUser(User user, HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		// 也可以按对象接受参数
//		JsonModel jModel = userService.addUser(user);
//		JsonUtil.objectToJson(jModel, response);
//
//	}

	/**
	 * 获取用户列表
	 */
	@RequestMapping("/getUserList")
	public void getJson(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Tuser> list = userService.getAllUser();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(JSON.toJSONString(list));
		out.flush();
		out.close();
	}

}
