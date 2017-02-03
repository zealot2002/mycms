
package org.zzy.mycms.domain;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = -533698031946372178L;

	private String username;
	private String password;

	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

}
