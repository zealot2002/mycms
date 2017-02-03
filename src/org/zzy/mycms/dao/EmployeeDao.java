
package org.zzy.mycms.dao;

import org.zzy.mycms.domain.Employee;


public interface EmployeeDao {

	/**
	 * 读取用户信息
	 * 
	 * @param username
	 * @return
	 */
	Employee read(String username);

}
