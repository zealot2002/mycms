package org.zzy.mycms.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzy.mycms.alias.Services;
import org.zzy.mycms.bean.Page;
import org.zzy.mycms.dao.ISupportDao;
import org.zzy.mycms.service.BaseService;



@Service(Services.ARTICLE)
public class ArticleService extends BaseService{


	@Autowired private ISupportDao commonDao;
	
	
	
	public Page find(int currentPageNo, int pageSize, Map<String,Object> condition) { 
		String keyword = (String) condition.get("keyword");
		String status = (String) condition.get("status");
		int cid = (Integer)condition.get("cid");
		try {
			keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
		Page page;
		if(cid == 0){
			String sql = "select a.*, concat('/cms?type=',c.model,'&id=',CAST(a.id as char),'&time=',DATE_FORMAT(a.time,'%Y%m%d'))  url, c.name as cname ,c.model from "+config.getPrefix()+"article as a "
					+ "left join "+config.getPrefix()+"category c on c.id = a.cid " 
					+ "where a.status in ("+status+") and a.title like ? order by a.id desc";
			page = commonDao.findByPage(currentPageNo, pageSize,  sql,'%'+keyword+'%'); 
		}else{
			String sql = "select a.*, concat('/cms?type=',c.model,'&id=',CAST(a.id as char),'&time=',DATE_FORMAT(a.time,'%Y%m%d'))  url, c.name  as cname ,c.model from "+config.getPrefix()+"article as a "
				+ "left join "+config.getPrefix()+"category c on c.id = a.cid " 
				+ "where a.status in ("+status+") and a.cid=? and a.title like ? order by a.id desc";
		
		
			page = commonDao.findByPage(currentPageNo, pageSize,  sql, cid,'%'+keyword+'%'); 
		}
		return page;
	}
	
}
