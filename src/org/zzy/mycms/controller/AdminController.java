
package org.zzy.mycms.controller;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zzy.mycms.model.ResultMessage;
import org.zzy.mycms.utils.HttpUtils;

@Controller
public class AdminController {

	private static final String viewPath = "admin/";
	@Autowired ServletContext application;
	
	/** 构造方法初始化一些成员变量 */
	public AdminController() {
	} 
	/** 后台主界面 */ 
	@RequestMapping(value=viewPath +"login", method=RequestMethod.GET)
	public String login(HttpServletRequest request){ 
		
		System.out.println("login");
		// 如果没有安装系统
//		if(!WebAPP.install)
//			return "redirect:../install/index.jsp";
		
		request.setAttribute("url", HttpUtils.getRequestURL(request)); 
		HttpSession session = request.getSession(false);
		if(session != null){
//			try{
//				int groupId = (Integer) session.getAttribute(AppStatic.WEB_APP_SESSSION_USER_GROUP_ID);
//				request.setAttribute("topmenus", menuDao.findTopMenuByGroupId(groupId)); 
//			}catch (Exception e) {
//				log.error("因为没有登录，在主页就不能查询到分组ID");
//				return "redirect:login.do";
//			}
		}
		return this.viewPath+"login";
	}
	
	/**
	 * 登录系统
	 * 验证码不区分大小写
	 * @return json
	 * */
	@RequestMapping(value=viewPath +"loginSystem", method=RequestMethod.POST)
	@ResponseBody
	public Object loginSystem(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String device   = request.getParameter("device");// 设备
		HttpSession session = request.getSession();// 如果会话不存在也就创建
		
		System.out.println("loginSystem");
		
		int errorCode = 0;// 登录日志类型
		String scode = "";
		
		request.setAttribute("url", HttpUtils.getRequestURL(request)); 
		request.setAttribute("loginusername", "ZZYZ	ZZZZZZZZZZZZZZZZZZZAAAAASSS");
		
		ResultMessage msg = new ResultMessage(true,"2!");
		
		return msg;
	}
	@RequestMapping(value=viewPath +"index", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		System.out.println("Welcome index!");
		request.setAttribute("url", HttpUtils.getRequestURL(request)); 
		request.setAttribute("loginusername", "ZZYZ	ZZZZZZZZZZZZZZZZZZZAAAAASSS");
		
		return this.viewPath+"index";
	}
	
	/**
	 * 系统信息
	 * */
	@RequestMapping(value=viewPath +"systeminfo")
	public ModelAndView systeminfo(){
		ModelAndView view = new ModelAndView(this.viewPath + "systeminfo");
		String os = System.getProperty("os.name");//操作系统名称
		String osVer = System.getProperty("os.version"); //操作系统版本    
		String javaVer = System.getProperty("java.version"); //操作系统版本
		String javaVendor = System.getProperty("java.vendor"); //操作系统版本
		
		Runtime runTime = Runtime.getRuntime();
		
		long freeM = runTime.freeMemory() / 1024 / 1024;
        long maxM  = runTime.maxMemory() / 1024 / 1024;
        long tM    = runTime.totalMemory() / 1024 / 1024; 
        view.addObject("freememory", freeM);
        view.addObject("maxmemory", maxM);
        view.addObject("totalmemory", tM);
		view.addObject("os", os);
		view.addObject("osver", osVer);
		view.addObject("javaver", javaVer);
		view.addObject("javavendor", javaVendor);
		view.addObject("currenttime", new Date());
		
		view.addObject("serverinfo", application.getServerInfo());
		view.addObject("dauthor", "zzy");
		view.addObject("email", "zzy_2002@126.com");
		view.addObject("version", "20140803");
		view.addObject("qqqun","22465566");
		view.addObject("uxqqqun","181150189");
		
		return view;
	}
}
