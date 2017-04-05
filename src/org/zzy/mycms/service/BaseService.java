package org.zzy.mycms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zzy.mycms.alias.LOG;
import org.zzy.mycms.config.impl.DataBaseConfig;


/**
 * 
 * 基础业务层
 * 
 * 
 * 提供：日志处理接口
 * 
 * @author marker
 * @version 1.0
 */
public class BaseService {

	/** 日志记录器 */ 
	protected Logger logger =  LoggerFactory.getLogger(LOG.SERVICE_LAYER);
	
	/** 数据库配置 */
	protected DataBaseConfig config = DataBaseConfig.getInstance(); 
	
}
