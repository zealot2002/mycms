package org.zzy.mycms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zzy.mycms.alias.DAO;
import org.zzy.mycms.bean.Category;
import org.zzy.mycms.dao.DaoEngine;
import org.zzy.mycms.dao.ICategoryDao;
import org.zzy.mycms.dao.mapper.ObjectRowMapper.RowMapperCategory;



/**
 * 分类Dao
 * 
 * @author marker
 * @version 1.0
 */
@Repository(DAO.CATEGORY)
public class CategoryDaoImpl extends DaoEngine implements ICategoryDao {

	
	
	
	public List<Category> list(){
		StringBuilder sql = new StringBuilder();
		sql.append("select c.*, m.name modelName from ").append(getPreFix())
		.append("category c ").append(" left join ").append(getPreFix())
		.append("model m on c.model=m.type order by c.sort asc") ;
		return this.jdbcTemplate.query(sql.toString(), new RowMapperCategory()); 
	}
	
	
	
}
