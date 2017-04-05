package org.zzy.mycms.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zzy.mycms.bean.ResultMessage;
import org.zzy.mycms.ext.message.MessageContext;
import org.zzy.mycms.support.SupportController;




/**
 * 后台管理主界面控制器
 * @author marker
 * 
 * */
@Controller
@RequestMapping("/admin/international")
public class InternationalController  extends SupportController {
	
	
	public InternationalController() {
		this.viewPath = "/admin/international/";
	}
	
	
	
	/**
	 * 创建
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public ResultMessage create(@RequestParam("sl") String sl){

		MessageContext mc = MessageContext.getInstance(); 
		  
		try {
			mc.create(sl);
		} catch (Exception e) {
			return new ResultMessage(true, "操作失败!<br/>"+e.getMessage());
		} 
		
		return new ResultMessage(true, "操作成功!");
	}
	
	
	/**
	 * 进入数据库配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView international(HttpServletRequest request){
		ModelAndView view = new ModelAndView(this.viewPath + "list"); 
		MessageContext mc = MessageContext.getInstance(); 
		view.addObject("langs", mc.getLanguages());
		view.addObject("select", mc.getSelectElement());
		return view;
	}


	/**
	 * 编辑语言
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request){
		ModelAndView view = new ModelAndView(this.viewPath + "edit"); 
		MessageContext mc = MessageContext.getInstance(); 
		view.addObject("data", mc.getDefaultProperties());
		return view;
	}
	
	/**
	 * 编辑语言key
	 * @param request
	 * @return
	 */
	@RequestMapping("/editkey")
	public ModelAndView editkey(HttpServletRequest request, @RequestParam("key") String key){
		ModelAndView view = new ModelAndView(this.viewPath + "editkey"); 
		MessageContext mc = MessageContext.getInstance();
		view.addObject("key", key); 
		view.addObject("langs", mc.getLanguages());
		view.addObject("mapdata", mc.getKeyMap(key));
		return view;
	}
	
	
	
	@RequestMapping("/addlang")
	public ModelAndView addlang(HttpServletRequest request){
		ModelAndView view = new ModelAndView(this.viewPath + "addlang"); 
		MessageContext mc = MessageContext.getInstance();
		view.addObject("langs", mc.getLanguages());
		return view;
	}
	
	
	
	
	/**
	 * 删除语言
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete( @RequestParam("lang") String lang){ 
		MessageContext mc = MessageContext.getInstance(); 
		try {
			mc.remove(lang);
		} catch (IOException e) { 
			return new ResultMessage(true, "操作失败!");
		} 
		return new ResultMessage(true, "操作成功!");
	}
	
	
	
	
	/**
	 * 保存国际化语言
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest req, @RequestParam("key") String key){ 
		
		MessageContext mc = MessageContext.getInstance(); 
		@SuppressWarnings("rawtypes")
		Map data = req.getParameterMap();
		
		Set<Object> sets = data.keySet();
		for(Object o : sets){
			String code = o.toString();
			boolean islang = code.contains("lang_");
			if(islang){
				String lang = code.substring(5, code.length()); 
				String value = req.getParameter(code);
				mc.setProperty(lang, key, value);// 设置键值对
			} 
		}
		try {
			mc.storeProperty();
		} catch (IOException e) { 
			return new ResultMessage(true, "操作失败!");
		} 
		return new ResultMessage(true, "操作成功!");
	}
	
	
	/**
	 * 删除语言
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletekey")
	@ResponseBody
	public Object deletekey( @RequestParam("key") String key){ 
		MessageContext mc = MessageContext.getInstance();
		
		try {
			mc.removeKey(key);
			mc.storeProperty();// 持久化
		} catch (IOException e) {
			return new ResultMessage(true, "操作失败!");
		} 
		return new ResultMessage(true, "操作成功!");
	}
	
	
	
	
}
