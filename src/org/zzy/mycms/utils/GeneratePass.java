package org.zzy.mycms.utils;

import org.zzy.mycms.config.impl.SystemConfig;
import org.zzy.security.Base64;
import org.zzy.security.DES;
import org.zzy.security.MD5;

/**
 * 密码加密生成器
 * 
 * @author marker
 * */
public class GeneratePass {

	private static final SystemConfig syscfg = SystemConfig.getInstance();
	
	public static String encode(String password) throws Exception {
		String key = syscfg.get("secret_key");
		 return MD5.getMD5Code(Base64.encode(DES.encrypt(
					password.getBytes(), key))); 
	}

}
