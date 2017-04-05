package org.zzy.mycms.dao.impl;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.alias.DAO;
import org.zzy.mycms.bean.Article;
import org.zzy.mycms.dao.DaoEngine;
import org.zzy.mycms.dao.IArticleDao;




@Repository(DAO.ARTICLE)
public class ArticleDaoImpl extends DaoEngine implements IArticleDao {
	
	
	
	
	@Override
	public boolean update(Article a) {
		String prefix = dbConfig.getPrefix();//获取数据库表前缀
		
		
		
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(prefix).append("article")
		.append(" set cid=?, title=?,keywords=?,description=?,author=?, content=?,status=?,source=?,icon=? where id=?");
		
		
		
		int status = jdbcTemplate.update(sql.toString(),a.getCid(),a.getTitle(),a.getKeywords(),a.getDescription()
				,a.getAuthor(),a.getContent(),a.getStatus(),a.getSource(),a.getIcon(),a.getId()); 
		return status>0? true : false; 
	}
	

}
