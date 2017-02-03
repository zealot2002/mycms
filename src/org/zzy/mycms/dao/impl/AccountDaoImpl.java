
package org.zzy.mycms.dao.impl;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.dao.AccountDao;
import org.zzy.mycms.domain.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zlex.spring.dao.AccountDao#read(java.lang.String)
	 */
	@Override
	public Account read(String username) {

		return new Account(username, "wolf");
	}

}
