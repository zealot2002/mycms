
package org.zlex.spring.controller;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zlex.spring.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/account.do",method = RequestMethod.GET)
//	public Map register(@RequestParam("username") String u,
//			@RequestParam("password") String p, ModelMap model) {
//
//		System.out.println(accountService.verify(u, p));
//		model.put("msg", u);
//		return model;
//	}
//	
//	@SuppressWarnings("unchecked")  
//	@RequestMapping(value = "/xxx.do",method = RequestMethod.GET)  
//	public String hello(String username, String password, ModelMap model) {  
//		System.out.println("hello");
//	    System.out.println(accountService.verify(username, password));  
//	    model.put("msg", username);  
//	    return "account";  
//	} 
//	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/login",method = RequestMethod.GET)  
	public String login(String username, String password, PrintWriter writer) {  
		System.out.println("login");
		try {
			writer.print(username+" login "+accountService.verify(username, password));
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";  
	}
}
