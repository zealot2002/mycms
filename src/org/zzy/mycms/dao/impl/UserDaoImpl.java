package org.zzy.mycms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.bean.User;
import org.zzy.mycms.bean.UserGroup;
import org.zzy.mycms.dao.DaoEngine;
import org.zzy.mycms.dao.IUserDao;
import org.zzy.mycms.dao.mapper.ObjectRowMapper.RowMapperUser;
import org.zzy.mycms.dao.mapper.ObjectRowMapper.RowMapperUserGroup;


@Repository("userDao")
public class UserDaoImpl extends DaoEngine implements IUserDao{

	/**
	 * 通过用户名和密码查询用户对象
	 * */
	@Override
	public User queryByNameAndPass(String name, String pass) {
		String prefix = dbConfig.getPrefix();
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(prefix).append("user").append(" where name=? and pass=?");
		User user = null; 
		try{
			user = this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{name, pass}, new RowMapperUser());
		}catch (Exception e) {}
		
		return user;
	}


	@Override
	public boolean updateLoginTime(Serializable id) {
		String prefix = dbConfig.getPrefix();
		StringBuilder sql = new StringBuilder("update ");
		sql.append(prefix).append("user ").append("set logintime=sysdate() where id=?");
		
		int status = jdbcTemplate.update(sql.toString(), id);
		return status>0?true:false;
	}


	/* (non-Javadoc)
	 * @see org.marker.mushroom.dao.IUserDao#findUserByName(java.lang.String)
	 */
	@Override
	public User findUserByName(String userName) {
		String prefix = dbConfig.getPrefix();
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(prefix).append("user").append(" where name=?");
		User user = null; 
		try{
			user = this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{userName}, new RowMapperUser());
		}catch (Exception e) {
			logger.error("通过name查询用户失败!", e);
		}
		
		return user; 
	}


	@Override
	public List<UserGroup> findGroup() {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getPreFix()).append("user_group"); 
		return this.jdbcTemplate.query(sql.toString(), new RowMapperUserGroup());
	}


	// 统计用户组的用户数量
	@Override
	public int countUserByGroupId(int groupId) {
		StringBuilder sql = new StringBuilder("select count(*) from ");
		sql.append(getPreFix()).append("user u where u.gid=? "); 
		
		return this.queryForObject(sql.toString(), Integer.class, new Object[]{groupId});
	}

}
