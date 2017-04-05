package org.zzy.mycms.dao;

import org.zzy.mycms.bean.Article;

public interface IArticleDao extends ISupportDao{
	
	public boolean update(Article entity);
	
}
