package com.ashish;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController
{

	@RequestMapping("/")
	public String start(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "index";
		
		if(session.getAttribute("type").toString().equals("admin"))
				return "redirect:/administrator";
		else
				return "redirect:/home";
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String defaultMethod()
	{
		return "404error";
	}
}
