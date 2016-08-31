package com.zsx.web.service;

import java.util.List;

import com.zsx.web.entity.Tuser;
import com.zsx.web.model.json.JsonModel;
/**
 * 用户管理service接口
 * @author zsx
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 */
	Tuser login(Tuser user);
	
	/**
	 * 注册时检查用户名是否已经存在
	 */
	JsonModel checkUsernameIsExist(String name);
	/**
	 * 获取所有的用户信息——需要改为只有管理员才可以看到
	 */
	List<Tuser> getAllUser();
//	/**
//	 * 根据用户id获取用户的所有权限，用于拦截器限制
//	 */
//	List<String> getUserPrivileges(String userId);
//	/**
//	 * 获取所有的角色信息——需要改为只有管理员才可以
//	 */
//	List<Trole> getAllRole();
//	/**
//	 * 根据用户id获取用户角色信息
//	 */
//	List<Tree> getUserRoleByUserId(String userId);
//	/**
//	 * 修改用户角色信息
//	 */
//	JsonModel updateUserRole(String userId, String roleIds);
//	/**
//	 * 添加角色
//	 */
//	JsonModel addrole(String name, String num);
//	/**
//	 * 修改角色
//	 */
//	JsonModel updaterole(String id, String name, String num);
//	/**
//	 * 删除角色
//	 */
//	JsonModel deleterole(String id);
//	/**
//	 * 获取所有的权限信息Tree
//	 */
//	List<Tree> getAllPrivilegeTree();
//	/**
//	 * 获取所有的权限信息Tree，根据角色id将其中属于角色的权限选中
//	 */
//	List<Tree> getPrivilegeTreeByRoleId(String roleId);
//	/**
//	 * 修改角色权限
//	 */
//	JsonModel updateRolePrivileges(String roleId, String ids);
//	/**
//	 * 获取菜单类权限tree
//	 */
//	List<Tree> getPrivilegeParentTree();
//	/**
//	 * 添加权限
//	 */
//	JsonModel addprivilege(String name, String url, String pid);
//	/**
//	 * 修改权限
//	 */
//	JsonModel updateprivilege(String id, String name, String url, String pid);
//	/**
//	 * 删除权限
//	 */
//	JsonModel deleteprivilege(String id);
}
