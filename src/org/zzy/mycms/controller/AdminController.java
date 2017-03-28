
package org.zzy.mycms.controller;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zzy.mycms.bean.ResultMessage;
import org.zzy.mycms.bean.User;
import org.zzy.mycms.constant.CommonConstant;
import org.zzy.mycms.dao.IMenuDao;
import org.zzy.mycms.service.IUserService;
import org.zzy.mycms.support.SupportController;
import org.zzy.mycms.utils.HttpUtils;

@Controller
public class AdminController extends SupportController{

	/** 日志记录器 */
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	private static final String viewPath = "admin/";
	@Autowired ServletContext application;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMenuDao menuDao;
	
	/** 构造方法初始化一些成员变量 */
	public AdminController() {
	} 
	/** 后台主界面 */ 
	@RequestMapping(value=viewPath +"login", method=RequestMethod.GET)
	public String login(HttpServletRequest request){ 
		
		System.out.println("login");
//		// 如果没有安装系统
////		if(!WebAPP.install)
////			return "redirect:../install/index.jsp";
//		
		request.setAttribute("url", HttpUtils.getRequestURL(request)); 
//		HttpSession session = request.getSession(false);
//		if(session != null){
//			try{
//				int groupId = (Integer) session.getAttribute(CommonConstant.WEB_APP_SESSSION_USER_GROUP_ID);
//				request.setAttribute("topmenus", menuDao.findTopMenuByGroupId(groupId)); 
//			}catch (Exception e) {
//				log.error("因为没有登录，在主页就不能查询到分组ID");
////				return "redirect:login.do";
//			}
//		}
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
		HttpSession session = request.getSession();// 如果会话不存在也就创建
		
		System.out.println("loginSystem");
		
		int errorCode = 0;// 登录日志类型
		String scode = "";
		
		request.setAttribute("url", HttpUtils.getRequestURL(request)); 
		request.setAttribute("loginusername", "ZZYZ	ZZZZZZZZZZZZZZZZZZZAAAAASSS");
		
		ResultMessage msg;
		
		try {
//			User user = userDao.queryByNameAndPass(username, password2);
			User user = userService.findUserByName(username);
			
			if (user != null) {
				if (user.getStatus() == 1) {// 启用
					userService.updateLoginTime(user.getId());// 更新登录时间
					session.setAttribute(CommonConstant.WEB_APP_SESSION_ADMIN, user);
					session.setAttribute(CommonConstant.WEB_APP_SESSSION_LOGINNAME, user.getName());
					session.setAttribute(CommonConstant.WEB_APP_SESSSION_USER_GROUP_ID, user.getGid());// 设置分组
					msg = new ResultMessage(true, "登录成功!");
				} else {
					errorCode = 1;
					msg = new ResultMessage(false, "用户已禁止登录!");
				}
			} else {
				errorCode = 1;
				msg = new ResultMessage(false, "用户名或者密码错误!");
			}
		} catch (Exception e) {
			errorCode = 1;
			msg = new ResultMessage(false, "系统加密算法异常!");
			log.error("系统加密算法异常!", e);
		}
		return msg;
	}
	@RequestMapping(value=viewPath +"index", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {

		request.setAttribute("url", HttpUtils.getRequestURL(request));
		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int groupId = (Integer) session.getAttribute(CommonConstant.WEB_APP_SESSSION_USER_GROUP_ID);
//				int groupId = 1;
				request.setAttribute("topmenus", menuDao.findTopMenuByGroupId(groupId));
			} catch (Exception e) {
				System.out.println("Exception:"+e.toString());
				log.error("因为没有登录，在主页就不能查询到分组ID");
//				return "redirect:login.do";
			}
		}

		return this.viewPath + "index";
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
	
	/**
	 * 子菜单接口
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value=viewPath +"childmenus")
	@ResponseBody
	public Object menu(HttpServletRequest request, @RequestParam("id") int id) {
		ModelAndView view = new ModelAndView(this.viewPath + "childmenus");
		view.addObject("menu", menuDao.findMenuById(id));
		HttpSession session = request.getSession();
		if (session != null) {
			try {
				int groupId = (Integer) session.getAttribute(CommonConstant.WEB_APP_SESSSION_USER_GROUP_ID);
				view.addObject("childmenus", menuDao.findChildMenuByGroupAndParentId(groupId, id));
			} catch (Exception e) {
				log.error("因为没有登录，在主页就不能查询到分组ID");
				return "<script>window.location.href='login.do?status=timeout';</script>";
			}
		}
		return view;
	}
}
