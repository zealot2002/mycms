package org.zzy.mycms.dao.impl;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.alias.DAO;
import org.zzy.mycms.dao.DaoEngine;
import org.zzy.mycms.dao.ICommonDao;
import org.zzy.mycms.dao.annotation.Entity;



/**
 * 通用数据库操作对象
 * @author marker
 * @date 2013-11-15 下午5:29:28
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
@Repository(DAO.COMMON)
public class CommonDaoImpl extends DaoEngine implements ICommonDao {

	


	// 批量删除
	@Override
	public boolean deleteByIds(Class<?> clzz, String ids) {
		String prefix = dbConfig.getPrefix();// 表前缀
		String tableName  = clzz.getAnnotation(Entity.class).value();
		String primaryKey = clzz.getAnnotation(Entity.class).key();
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ").append(prefix).append(tableName)
				.append(" where ").append(primaryKey).append(" in(")
				.append(ids).append(")");
		logger(sql.toString());
		return jdbcTemplate.update(sql.toString()) > 0 ? true : false;
	}
	
}
