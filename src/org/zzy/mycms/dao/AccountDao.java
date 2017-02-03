
package org.zzy.mycms.dao;

import org.zzy.mycms.domain.Account;


public interface AccountDao {

	/**
	 * 读取用户信息
	 * 
	 * @param username
	 * @return
	 */
	Account read(String username);

}
