package com.ashish;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController
{

	@GetMapping("/")
	public String start(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "index";
		
		if(session.getAttribute("roleId").toString().equals("1"))
				return "redirect:/administrator";
		else
				return "redirect:/home";
		
	}
	
	@GetMapping
	public String defaultMethod()
	{
		return "404error";
	}
}
