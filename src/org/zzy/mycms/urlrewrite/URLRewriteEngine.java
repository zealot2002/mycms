package org.zzy.mycms.urlrewrite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 


/**
 * URL重写
 * 参数 urlPattern 对应的处理接口地址
 * (采用URL规则方式实现，此版本为没有优化的版本，但处理速度也是相当快的)
 * 
 * //		ruleParameter.put("{channel}", new Parameter("p","{channel}","([a-zA-Z_0-9]+)"));
//		ruleParameter.put("{type}", new Parameter("type","{type}","([a-zA-Z_0-9]+)"));
//		ruleParameter.put("{id}", new Parameter("id","{id}","([0-9]+)"));
//		
//		//初始化两个规则
//		putRule("列表","{channel}.html");
//		putRule("内容","{channel}/{type}/cms-thread-{id}.html");
 * @author marker
 * @date 2013-05-06
 * */
public final class URLRewriteEngine {
	
	/** 参数正则表达式 */
	public static final Pattern parameterPattern = Pattern.compile("\\{[a-zA-Z_0-9]+\\}");
	
	/** 编码URL时地址纠正使用的正则表达式 */
	private static final Pattern RIGHT_URL = Pattern.compile("\\w+\\=");
	
	/** 规则参数 */
	public final Map<String,Parameter> ruleParameter = new HashMap<String,Parameter>();
	
	/** 维护规则对象 */
	private final Map<String,RewriteRule> rules = new HashMap<String, RewriteRule>(5);
	
	

	// 需要重写的URL接口地址
	private String urlPattern; 
	
	/** 默认构造 */
	public URLRewriteEngine(String urlPattern){
		this.urlPattern = urlPattern;
	}
	
	
	/**
	 * 添加规则参数
	 * @param Parameter param
	 * @return Parameter
	 * */
	public Parameter put(Parameter param){
		return ruleParameter.put(param.getExpress(), param);
	}
	
	/**
	 * 移除规则
	 * @param key 规则名称
	 * */
	public RewriteRule remove(String key){
		return rules.remove(key);
	}
	
	
	
	/**
	 * 设置规则
	 * @param key 标识
	 * @param urlrule 规则
	 * */
	public void putRule(String key, String urlrule) {
		Matcher matcher = parameterPattern.matcher(urlrule);
		String inRegex = new String(urlrule);// 解析好的URL正则表达式
		String inUrl    = "";// 真实访问地址
		String outRegex = "";// 出站regex
		String outUrl = new String(urlrule);
		int sequence = 1;
		while(matcher.find()){
			Parameter param = ruleParameter.get(matcher.group());
			if(param != null){
				inUrl += param.getKey() + "=$" + sequence +"&";
				outRegex  += param.getKey() + "="+param.getRegex()+"&";
				outUrl = outUrl.replaceAll(Pattern.quote(param.getExpress()), "\\$"+sequence++);
			}
		} 
		for(String express : ruleParameter.keySet()){
			Parameter param = ruleParameter.get(express);
			inRegex = inRegex.replaceAll(Pattern.quote(express), param.getRegex()); 
		}
		
		if(inUrl.length() > 1){
			inUrl    = inUrl.substring(0, inUrl.length() - 1); 
			outRegex = outRegex.substring(0, outRegex.length() - 1); 
		} 
		
		//正则 
		Pattern inPattern  = Pattern.compile(inRegex);
		Pattern outPattern = Pattern.compile(urlPattern.substring(0, urlPattern.length()-1)+"\\?" + outRegex);
		 
		RewriteRule rule = new RewriteRule(inPattern,urlPattern+inUrl, outPattern,outUrl);
//		System.out.println(rule.toString());
		rules.put(key, rule);
	}

 
	
	/**
	 * 解码
	 * @param url 被解码的URL
	 * */
	public String decoder(String url){
		Set<String> sets = rules.keySet();
		Iterator<String> it = sets.iterator();
		while(it.hasNext()){
			RewriteRule rule = rules.get(it.next()); 
			Matcher m = rule.inPattern.matcher(url);
			if(m.matches()){ 
				return m.replaceAll(rule.inResult);
			}
		}
		return url;
	}
	
 
	
	/**
	 * 编码(将普通url地址编码为伪静态地址)
	 * */
	public String encoder(String url){
		for(String key : rules.keySet()){
			RewriteRule rule = rules.get(key);
			/* URL地址纠正 start */
			Matcher ma = RIGHT_URL.matcher(rule.outPattern.toString());
			String urla = "";
			while(ma.find()){
				String field = ma.group().split("=")[0];
				int findex = url.indexOf(field);
				if(findex == -1) continue;
				int and = 0;
				if(url.lastIndexOf("&") == findex-1 || url.lastIndexOf("&") == -1)//代表最后一个
					and = url.length();
				else
					and = url.indexOf("&", findex); 
				urla += url.substring(findex, and)+"&";
			}
			if(urla.length() > 1){
				urla = urla.substring(0, urla.length()-1);
			
			}
			int a = url.indexOf("?");
			if(a != -1){//如果有问号
				urla = url.substring(0,a+1) + urla; 
			}
			/* URL地址纠正 end */
			
			Matcher m = rule.outPattern.matcher( urla ); 
			if(m.find()){
				return m.replaceAll(rule.outResult);
			}
		}
		return url;
	}


	
	
	
	public String getUrlPattern() {
		return urlPattern;
	}


	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	
	
}
