package com.ashish;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.model.JobSeekerDetails;
import com.ashish.service.UsersService;

@Controller
public class CommonController 
{
	@Autowired
	private UsersService usersService;

	@RequestMapping("/register")
	public ModelAndView register(HttpSession session)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("register");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		String user=req.getParameter("user");
		String password=req.getParameter("pass");
		if(user==null || password==null || user.trim().equals("") || password.trim().equals(""))
			return new ModelAndView("index","message","Invalid Username or Password");
		Integer roleId=this.usersService.login(user, password);
		if(roleId!=null)
		{
			session.setAttribute("name", user);
			session.setAttribute("roleId",roleId);
			return new ModelAndView("redirect:/home");
		}
		else
		{
			return new ModelAndView("index","message","Invalid Username or Password");
		}
		
	}
	
	@RequestMapping("/change_password")
	public ModelAndView change_password(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(session.getAttribute("roleId").equals("1"))
			return new ModelAndView("redirect:/administrator");
		return new ModelAndView("passwordchange");
	}
	
	@RequestMapping("/change")
	public ModelAndView change_pass(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(session.getAttribute("roleId").toString().equals("1"))
			return new ModelAndView("redirect:/");
		String password_new=req.getParameter("password_new");
		String password=req.getParameter("password");
		String user=(String)session.getAttribute("name");
		if(password_new==null || password==null ||password_new.trim().equals("") || password.trim().equals(""))
			return new ModelAndView("passwordchange","msg","Either Username or password is incorrect");
		if(password_new.equals(password))
			return new ModelAndView("passwordchange","msg","New Password cannot be same as old one");
		boolean status=this.usersService.changePassword(user, password, password_new);
		if(status)
		{
			session.invalidate();
			return new ModelAndView("success");
		}
		else
			return new ModelAndView("passwordchange","msg","Current password is incorrect");
	}
	
	@RequestMapping("/home")
	public ModelAndView homepage(HttpSession session)
	{
		if(session.getAttribute("roleId").toString().equals("2"))
		{
			List<AppliedJobs> jb_detail=this.usersService.getAppliedJobs(session.getAttribute("name").toString());
			return new ModelAndView("welcomepage","jobs",jb_detail);
		}
		else if(session.getAttribute("roleId").toString().equals("3"))
		{
			List<JobDetails> jb_detail=this.usersService.getPostedJobs(session.getAttribute("name").toString());
			return new ModelAndView("welcomepage","jb_detail",jb_detail);
		}
		else
			return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/forgot_password")
	public ModelAndView forgetPassword(HttpSession session)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("forget_password");
		
	}
	
	@RequestMapping("/search_user")
	public ModelAndView searchUser(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		String data=req.getParameter("user");
		if(data==null || data.trim().equals(""))
			return new ModelAndView("forget_password","reply","Field cannot be empty");
		Integer type=this.usersService.isUserExists(data);
		if(type!=null)
		{
			session.setAttribute("username", data);
			if(type==2)
			{
				return new ModelAndView("recover_password","type","jobseeker");
			}
			else
			{
				return new ModelAndView("recover_password","type","employer");
			}
		}
		else
			return new ModelAndView("forget_password","reply","User does not exists");
	}
	
	@RequestMapping("confirm_recover")
	public ModelAndView confirmRecover(HttpSession session,HttpServletRequest req,HttpServletResponse res)
	{

		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/");
		String password=req.getParameter("password_new");
		String cnf_password=req.getParameter("password_cnf");
		if(password==null || cnf_password==null || password.trim().equals("")||cnf_password.trim().equals(""))
			return new ModelAndView("password_recovery");
		if(!password.equals(cnf_password))
			return new ModelAndView("password_recovery");
		if(this.usersService.changePassword(session.getAttribute("username").toString(), password))
			return new ModelAndView("success");
		else
			return new ModelAndView("password_recovery");
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile(HttpSession session,Model model)
	{
		String msg="";
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		
		if(model.asMap().size()>0)
			msg = (String) model.asMap().get("msg");
		
		if(session.getAttribute("roleId").toString().equals("2"))
		{
			JobSeekerDetails jbs=this.usersService.getProfile(session.getAttribute("name").toString());
			ModelAndView view= new ModelAndView("profile","jobsekkerdetails",jbs);
			if(!msg.equals(""))
				view.addObject("msg",msg);
			return view;
		}
		else if(session.getAttribute("roleId").toString().equals("3"))
		{
			Employer emp=this.usersService.getProfile(session.getAttribute("name").toString(),true);
			ModelAndView view= new ModelAndView("profile","empdetails",emp);
			if(!msg.equals(""))
				view.addObject("msg",msg);
			return view;
		}
		else
			return new ModelAndView("redirect:/");
		
	}
	
	@RequestMapping("/editprofile")
	public ModelAndView editprofile(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		
		if(session.getAttribute("roleId").toString().equals("2"))
		{
			JobSeekerDetails jbs=this.usersService.getProfile(session.getAttribute("name").toString());
			return new ModelAndView("editprofile","jobsekkerdetails",jbs);
		}
		else if(session.getAttribute("roleId").toString().equals("3"))
		{
			Employer emp=this.usersService.getProfile(session.getAttribute("name").toString(),true);
			return new ModelAndView("editprofile","empdetails",emp);
		}
		else
			return new ModelAndView("redirect:/");
		
	}
}
