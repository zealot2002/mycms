package org.zzy.mycms.dao;
 
import java.io.Serializable;
import java.util.List;

import org.zzy.mycms.bean.User;
import org.zzy.mycms.bean.UserGroup;


public interface IUserDao {

	User queryByNameAndPass(String name, String pass);
	
	boolean updateLoginTime(Serializable id);

	
	/**
	 * @param userName
	 * @return
	 */
	User findUserByName(String userName);
	
	/**
	 * 查询所有用户分组
	 * @return
	 */
	List<UserGroup> findGroup();
	
	
	
	/**
	 * 统计用户组的用户数量
	 * @param groupId
	 * @return
	 */
	int countUserByGroupId(int groupId);
}
