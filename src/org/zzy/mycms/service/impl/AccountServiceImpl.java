package org.zzy.mycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzy.mycms.dao.AccountDao;
import org.zzy.mycms.domain.Account;
import org.zzy.mycms.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;
	@Override
	public boolean verify(String username, String password) {
		Account account = accountDao.read(username);
		if (password.equals(account.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
