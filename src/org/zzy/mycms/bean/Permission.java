package org.zzy.mycms.bean;

import java.io.Serializable;

import org.zzy.mycms.dao.annotation.Entity;


/**
 * 用户权限
 * @author Administrator
 *
 */
@Entity("user_group_menu")
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 6763161302556198431L;

	/** 分组的ID */
	private int gid;

	/** 分组的ID */
	private int mid;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	
}
