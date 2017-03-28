package org.zzy.mycms.bean;

import java.io.Serializable;
import java.util.Date;

import org.zzy.mycms.dao.annotation.Entity;

@Entity("guestbook")
public class GuestBook implements Serializable { 
	private static final long serialVersionUID = -4292951929182132458L;

	private int id;
	private String content;
	private String ip;
	private String nickname;
	private Date time;
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
