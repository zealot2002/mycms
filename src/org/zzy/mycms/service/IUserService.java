package org.zzy.mycms.service;

import org.zzy.mycms.bean.User;

public interface IUserService {
	User findUserByName(String userName);

	boolean updateLoginTime(int id);
}
