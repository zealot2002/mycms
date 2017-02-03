
package org.zlex.spring.dao;

import org.zlex.spring.domain.Account;


public interface AccountDao {

	/**
	 * 读取用户信息
	 * 
	 * @param username
	 * @return
	 */
	Account read(String username);

}
