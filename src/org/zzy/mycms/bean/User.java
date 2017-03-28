package org.zzy.mycms.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.zzy.mycms.dao.annotation.Entity;


/**
 * 用户对象
 * @author marker
 * */
@Entity("user")
public class User implements Serializable{
	
	private static final long serialVersionUID = -3931877820780528915L;
	
	/** 自动生成ID */
	private int id;
	/** 分组ID */
	private int gid;
	private String nickname;
	private String name;
	private String pass;
	private Date createtime;
	private Date logintime;
	private int status;
	private String description;
	
	private List<Role> roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	
	
	
	
	
	
	 
	
	
}
