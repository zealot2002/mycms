package org.zzy.mycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzy.mycms.bean.User;
import org.zzy.mycms.dao.IUserDao;
import org.zzy.mycms.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(userName);
	}

	@Override
	public boolean updateLoginTime(int id) {
		// TODO Auto-generated method stub
		return userDao.updateLoginTime(id);
	}
}
