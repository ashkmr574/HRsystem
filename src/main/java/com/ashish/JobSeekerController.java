package com.ashish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.model.JobSeekerDetails;
import com.ashish.service.JobSeekerService;
import com.ashish.validator.MyCustomValidator;

@Controller
public class JobSeekerController
{
	private JobSeekerService jobSeekerService;
	
	@Autowired
	public void setJobSeekerService(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}
	
	@InitBinder
	 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
	     final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	     binder.registerCustomEditor(double.class, new MyCustomValidator(Double.class));
	 }
	
	@RequestMapping("/register_jobseeker")
	public ModelAndView mymethod(@Valid @ModelAttribute("jobseeker") JobSeekerDetails jbs,BindingResult result,HttpSession session)
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		if(jbs==null || jbs.getUsers()==null || jbs.getEducationaldetails()==null || jbs.getPersonaldetails()==null || jbs.getProfessionaldetails()==null)
			return new ModelAndView("register","jobsekkerdetails",jbs);
		if(result.hasErrors())
		{
			Map<String,String> map=new HashMap<String,String>();
			for(FieldError s:result.getFieldErrors())
			{
					map.put(s.getField(),s.getDefaultMessage());
			}
			ModelAndView model=new ModelAndView("register","jobsekkerdetails",jbs);
			model.addObject("error",map);
			return model;
		}
		this.jobSeekerService.saveJobSeeker(jbs);
		return new ModelAndView("success_registration");
	}
	
	@RequestMapping(value="/checkemail",method = RequestMethod.POST)
	public @ResponseBody String checkemail(HttpServletRequest req,HttpServletResponse res)
	{
		Boolean Status=this.jobSeekerService.isemailexists(req.getParameter("email"));
		if(Status)
			return "email exists";
		else
			return "email available";
	}
	
	@RequestMapping(value="/checkuser", method = RequestMethod.POST)
	public @ResponseBody String checkuser(HttpServletRequest req,HttpServletResponse res)
	{
		Boolean Status=this.jobSeekerService.isuserexists(req.getParameter("user"));
		if(Status)
			return "user exists";
		else
			return "user available";
	}
	
	@RequestMapping(value="/getinfo",method = RequestMethod.POST)
	public @ResponseBody String getinfo(HttpServletRequest req,HttpServletResponse res)
	{
		String message=this.jobSeekerService.getProfile(req.getParameter("user")).getPersonaldetails().toString();
		return message;
	}
	
	@RequestMapping("jobseeker_recover")
	public ModelAndView recoverJobseeker(HttpSession session,HttpServletRequest req,HttpServletResponse res) throws ParseException
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/");
		String username=session.getAttribute("username").toString();
		String email=req.getParameter("email");
		String date=req.getParameter("dob");
		if(email==null || date==null || email.trim().equals("")|| date.trim().equals(""))
		{
			ModelAndView model=new ModelAndView("recover_password","type","jobseeker");
			model.addObject("msg","Data you have provided is incorrect");
			return model;
		}
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);  
		boolean status=this.jobSeekerService.validate(email,date1,username);
		if(status)
		{
			return new ModelAndView("password_recovery");
		}
		else
		{
			ModelAndView model=new ModelAndView("recover_password","type","jobseeker");
			model.addObject("msg","Data you have provided is incorrect");
			return model;
		}
	}
	
	@RequestMapping("/submiteditedprofile")
	public ModelAndView submiteditedprofile(HttpSession session,@Valid @ModelAttribute("")JobSeekerDetails js,BindingResult result, @ModelAttribute("") Employer emp,final RedirectAttributes redirectAttributes) throws ParseException
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return new ModelAndView("redirect:/");
		if(result.hasErrors())
		{
			Map<String,String> map=new HashMap<String,String>();
			for(FieldError s:result.getFieldErrors())
			{
					map.put(s.getField(),s.getDefaultMessage());
			}
			ModelAndView model= new ModelAndView("editprofile","jobsekkerdetails",js);
			model.addObject("error",map);
			return model;
		}
			this.jobSeekerService.editProfile(js,session.getAttribute("name").toString());
			redirectAttributes.addFlashAttribute("msg", "success");
			return new ModelAndView("redirect:/profile");
	}
	
	@RequestMapping("/search_jobs")
	public String job_search(Model model,HttpSession session) throws ParseException
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return "unauthorized";
		
		List<JobDetails> jb_detail=this.jobSeekerService.getJobsList(session.getAttribute("name").toString());
		model.addAttribute("jb_detail",jb_detail);
		return "job_list";
	}
	

	@RequestMapping("/criteria_search")
	public String criteria_search(Model model,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws ParseException
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return "unauthorized";
		
		String criteria=req.getParameter("criteria");
		String text=req.getParameter("search");
		if(criteria==null || text==null || criteria.trim().equals("")|| text.trim().equals(""))
			return "redirect:/search_jobs";
		List<JobDetails> jb_detail=this.jobSeekerService.getJobsList(text,criteria);
		model.addAttribute("jb_detail",jb_detail);
		model.addAttribute("search",text);
		model.addAttribute("criteria",criteria);
		return "job_list";
	}
	

	@RequestMapping("/apply_submit")
	public String apply_submit(Model model,HttpSession session)
	{
		if(model.asMap().size()==0)
			return "redirect:/search_jobs";
		int id = (int) model.asMap().get("id");
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return "unauthorized";
		boolean isblocked=this.jobSeekerService.isBlocked(session.getAttribute("name").toString(),this.jobSeekerService.getCompanyUserName(id) );
		if(!isblocked)
		{
			String contact=this.jobSeekerService.getContact(session.getAttribute("name").toString());
			String name=this.jobSeekerService.getApplicantName(session.getAttribute("name").toString());
			JobDetails jb_detail=this.jobSeekerService.getJob(id);
			model.addAttribute("applicant_name",name);
			model.addAttribute("jb_detail",jb_detail);
			model.addAttribute("applicant_contact",contact);
			return "applyjobs";
		}
		else
			return "errorapply";
		
	}
	@RequestMapping("/apply_{id}")
	public String apply_job(@PathVariable("id") int id,Model model,final RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("id",id);
		return "redirect:/apply_submit";
	}
	
	@RequestMapping("/finalsubmit")
	public String submitjob(HttpSession session,@Valid @ModelAttribute("appliedjobs") AppliedJobs jobs,BindingResult result) throws ParseException
	{
		
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return "redirect:/";
		if(result.hasErrors())
		{
			return "redirect:/";
		}
		jobs.getJobid().setApplicant_username(session.getAttribute("name").toString());
		this.jobSeekerService.saveAppliedJobs(jobs);
		return "registred_success";
	}
	
	@RequestMapping("/withdraw_{id}")
	public ModelAndView withdraw(@PathVariable("id") int id,HttpSession session,final RedirectAttributes redirectAttributes)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("type").toString().equals("jobseeker"))
			return new ModelAndView("unauthorized");
		this.jobSeekerService.withdraw(id,session.getAttribute("name").toString());
		redirectAttributes.addFlashAttribute("withdraw","withdrawn");
		return new ModelAndView("redirect:/home");
	}
	
}
