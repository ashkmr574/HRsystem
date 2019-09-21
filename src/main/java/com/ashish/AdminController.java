package com.ashish;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ashish.model.AdminModel;
import com.ashish.service.AdminService;

@Controller
public class AdminController
{
	@Autowired
	private AdminService adminService;

	@RequestMapping("/administrator")
	public ModelAndView admin(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("admin_login");
		
		if(!session.getAttribute("roleId").toString().equals("1"))
			return new ModelAndView("redirect:/");

		return new ModelAndView("redirect:/adminhome");
	}
	
	@RequestMapping("/adminlogin")
	public ModelAndView adminlogin(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/administrator");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		if(username==null && password==null)
			return new ModelAndView("redirect:/administrator");
		
		String message=this.adminService.login(username,password);
		if(message.equals("loginsuccess"))
		{
			session.setAttribute("name",req.getParameter("username").toString());
			session.setAttribute("type","admin");
			session.setAttribute("firstlogin","false");
			return new ModelAndView("redirect:/adminhome");
		}
		else if(message.equals("firstlogin"))
		{
			session.setAttribute("name",req.getParameter("username").toString());
			session.setAttribute("type","admin");
			session.setAttribute("firstlogin","true");
			return new ModelAndView("change_password");
		}
		else
			return new ModelAndView("admin_login","message","Invalid Username or Password");
						
	}
	
	@RequestMapping("/change_adminpassword")
	public String changeadminpassword(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("1"))
			return "redirect:/";
		return "change_password";
	}
	
	@RequestMapping("/changepassword")
	public ModelAndView changepassword(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("1"))
			return new ModelAndView("redirect:/");
		String password_new=req.getParameter("password_new");
		String password=req.getParameter("password");
		if(password==null||password_new==null || password.trim().equals("") || password_new.trim().equals(""))
			return new ModelAndView("change_password","msg","Current password is incorrect");
		if(password.equals(password_new))
			return new ModelAndView("change_password","msg","You are using an old password");
		String user=(String)session.getAttribute("name");
		String firstlogin=(String)session.getAttribute("firstlogin");
		Boolean status=this.adminService.change_password(user, password, password_new, firstlogin);
		if(status)
		{
			session.invalidate();
			return new ModelAndView("success_admin");
		}
		else
			return new ModelAndView("change_password","msg","Current password is incorrect");
	}
	
	@RequestMapping("/adminhome")
	public ModelAndView adminHome(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/administrator");
		if(!session.getAttribute("roleId").toString().equals("1"))
			return new ModelAndView("redirect:/");
		//if(session.getAttribute("firstlogin").toString().equals("true"))
			//return new ModelAndView("redirect:/change_adminpassword");
		ModelAndView model=new ModelAndView("admin_panel");
		model.addObject("employer_count",this.adminService.getEmployerCount());
		model.addObject("jobseeker_count",this.adminService.getJobSeekerCount());
		model.addObject("job_count",this.adminService.getJobCount());
		model.addObject("applied_job_count",this.adminService.getAppliedJobCount());
		model.addObject("employer",this.adminService.getEmployers());
		model.addObject("jobseeker",this.adminService.getJobSeekers());
		model.addObject("jobs",this.adminService.getJobs(true));
		return model;
	}
	
	@RequestMapping("/add_admin")
	public String addAdmin(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("1"))
			return "redirect:/";
		return "add_admin";
	}
	
	@RequestMapping(value="/register_admin",method = RequestMethod.POST)
	public ModelAndView registerAdmin(HttpSession session,@Valid @ModelAttribute("") AdminModel model,BindingResult result)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").equals("1"))
			return new ModelAndView("redirect:/");
		if(model==null)
			return new ModelAndView("redirect:/add_admin");
		if(result.hasErrors())
		{
			Map<String,String> map=new HashMap<>();
			for(FieldError s:result.getFieldErrors())
			{
					map.put(s.getField(),s.getDefaultMessage());
			}
			ModelAndView data=new ModelAndView("add_admin");
			data.addObject("model",model);
			data.addObject("error",map);
			return data;
		}
		if(this.adminService.saveAdmin(model))
			return new ModelAndView("success_admin_registration");
		else
		{
			ModelAndView data=new ModelAndView("add_admin","msg","username alreday exists");
			data.addObject("model",model);
			return data;
		}
	}
}