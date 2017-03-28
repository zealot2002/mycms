package org.zzy.mycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zzy.mycms.dao.IEmployeeDao;
import org.zzy.mycms.model.Employee;
import org.zzy.mycms.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeDao employeeDao;
	@Override
	public boolean verify(String username, String password) {
		Employee employee = employeeDao.selectByPrimaryKey(2);
		if (password.equals(employee.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
