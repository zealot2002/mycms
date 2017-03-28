package org.zzy.mycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzy.mycms.dao.IMenuDao;
import org.zzy.mycms.service.IMenuService;
@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private IMenuDao menuDao;


	@Override
	public Object findTopMenuByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return menuDao.findTopMenuByGroupId(groupId);
	}
}
