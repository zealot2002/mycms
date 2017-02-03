package org.zzy.mycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzy.mycms.dao.EmployeeDao;
import org.zzy.mycms.domain.Employee;
import org.zzy.mycms.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public boolean verify(String username, String password) {
		Employee employee = employeeDao.read(username);
		if (password.equals(employee.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
