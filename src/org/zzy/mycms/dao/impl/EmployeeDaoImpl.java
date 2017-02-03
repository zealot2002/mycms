
package org.zzy.mycms.dao.impl;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.dao.EmployeeDao;
import org.zzy.mycms.domain.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zlex.spring.dao.AccountDao#read(java.lang.String)
	 */
	@Override
	public Employee read(String username) {

		return new Employee(username, "wolf");
	}

}
