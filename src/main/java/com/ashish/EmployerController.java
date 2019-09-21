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
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.BlockId;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.LockCandidate;
import com.ashish.model.EmployerDetails;
import com.ashish.service.EmployerService;
import com.ashish.validator.MyCustomValidator;

@Controller
public class EmployerController
{
	@Autowired
	private EmployerService employerService;
	
	@InitBinder
	public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) 
	{
	    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    binder.registerCustomEditor(double.class, new MyCustomValidator(Double.class));
	    binder.registerCustomEditor(int.class,new MyCustomValidator(Integer.class));
	    binder.registerCustomEditor(float.class,new MyCustomValidator(Float.class));
	 }
	
	@RequestMapping(value="/register_employer",method = RequestMethod.POST)
	public ModelAndView employerregister_control(@Valid @ModelAttribute("employer1") EmployerDetails emp,BindingResult result,HttpSession session) throws ParseException
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		if(emp==null || emp.getUsers()==null || emp.getEmployer()==null)
			return new ModelAndView("register","empdetails", emp);
		if(result.hasErrors())
		{
			Map<String,String> map=new HashMap<String,String>();
			for(FieldError s:result.getFieldErrors())
			{
					map.put(s.getField(),s.getDefaultMessage());
			}
			ModelAndView model=new ModelAndView("register","empdetails",emp);
			model.addObject("error",map);
			model.addObject("type","employer");
			return model;
		}
		boolean status=this.employerService.saveEmployer(emp);
		if(status)
			return new ModelAndView("success_registration");
		else
		{
			ModelAndView model=new ModelAndView("register","msg","user already exists");
			model.addObject("empdetails", emp);
			model.addObject("type","employer");
			return model;
		}
	}
	
	@RequestMapping("employer_recover")
	public ModelAndView recoverEmployer(HttpSession session,HttpServletRequest req,HttpServletResponse res) throws ParseException
	{
		if(session.getAttribute("name")!=null)
			return new ModelAndView("redirect:/");
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/");
		String username=session.getAttribute("username").toString();
		String email=req.getParameter("email");
		String date=req.getParameter("reg_date");
		if(email==null || date==null || email.trim().equals("")|| date.trim().equals(""))
		{
			
			ModelAndView model=new ModelAndView("recover_password","type","employer");
			model.addObject("msg","Data you have provided is incorrect");
			return model;
		}
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);  
		boolean status=this.employerService.validate(email,date1,username);
		if(status)
		{
			return new ModelAndView("password_recovery");
		}
		else
		{
			ModelAndView model=new ModelAndView("recover_password","type","employer");
			model.addObject("msg","Data you have provided is incorrect");
			return model;
		}
	}
	
	@RequestMapping("/submiteditprofile")
	public ModelAndView submiteditprofile(HttpSession session,@Valid @ModelAttribute("") Employer emp,BindingResult result,final RedirectAttributes redirectAttributes)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("redirect:/");
		if(result.hasErrors())
		{
				return new ModelAndView("editprofile","empdetails",emp);
		}
		emp.setUsername(session.getAttribute("name").toString());
		this.employerService.editProfile(emp);
		redirectAttributes.addFlashAttribute("msg","success");
		return new ModelAndView("redirect:/profile");
	}
	
	@RequestMapping("/post_jobs")
	public ModelAndView job_details(HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("unauthorized");
		return new ModelAndView("post_jobs");
	}
	
	@RequestMapping("/submit_job")
	public ModelAndView submit_job(HttpSession session,@Valid @ModelAttribute("jbdetails") JobDetails jb,BindingResult result)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("unauthorized");
		if(result.hasErrors())
		{
			Map<String,String> map=new HashMap<String,String>();
			for(FieldError s:result.getFieldErrors())
			{
					map.put(s.getField(),s.getDefaultMessage());
			}
			ModelAndView model=new ModelAndView("post_jobs","jbdetails",jb);
			model.addObject("error",map);
			return model;
		}
		jb.setUsername(session.getAttribute("name").toString());	
		this.employerService.saveJob(jb);
		return new ModelAndView("success_jobpost");
	}
	
	@RequestMapping("/candidate_management")
	public String candidate_management(HttpSession session,Model model)
	{
		if(session.getAttribute("name")!=null)
		{
			if(session.getAttribute("roleId").toString().equals("3"))
			{
				List<AppliedJobs> jobs=this.employerService.getAppliedJobs(session.getAttribute("name").toString());
				List<BlockCandidate> candidate=this.employerService.getBlockedCandidate(session.getAttribute("name").toString());
				model.addAttribute("jobs",jobs);
				model.addAttribute("candidate",candidate);
				return "candidatemanagement";
			}
			else
				return "unauthorized";
		}
		else
			return "redirect:/";
	}
	
	@RequestMapping("/lock_{id}_{name}")
	public String lock_candidate(@PathVariable("id") int id,@PathVariable("name") String name, HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("3"))
			return "unauthorized";
		boolean status=this.employerService.lock(id,name,session.getAttribute("name").toString());
		if(status)
				return "redirect:/candidate_management";
		else
			return "unauthorized";
	}
	
	@RequestMapping("/block_{name}")
	public String block_candidate(@PathVariable("name") String name, HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("3"))
			return "unauthorized";
		boolean status=this.employerService.block(name,session.getAttribute("name").toString());
		if(status)
			return "redirect:/candidate_management";
		else
			return "unauthorized";
	}
	
	@RequestMapping("/edit_{id}")
	public ModelAndView editjob(@PathVariable("id") int id,HttpSession session,final RedirectAttributes redirectAttributes)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("unauthorized");
		redirectAttributes.addFlashAttribute("id",id);
		return new ModelAndView("redirect:/editjob");
	}
	@RequestMapping("/editjobs")
	public ModelAndView editjobsredirect(HttpSession session,@ModelAttribute("jbdetails") JobDetails jb,final RedirectAttributes redirectAttributes)
	{

		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("unauthorized");
		jb.setUsername(session.getAttribute("name").toString());
		jb.setCompany_name(this.employerService.getCompanyName(jb.getUsername()));
		this.employerService.updateJob(jb);
		redirectAttributes.addFlashAttribute("editjob","true");
		ModelAndView model= new ModelAndView("redirect:/home");
		return model;
	}
	@RequestMapping("/editjob")
	public ModelAndView editjobs(Model model,HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("redirect:/");
		if(model.asMap().size()==0)
			return new ModelAndView("redirect:/");
		int id = (int) model.asMap().get("id");
		JobDetails jb_detail=this.employerService.getJob(id);
		return new ModelAndView("editjob","jb_detail",jb_detail);
	}	
	
	@RequestMapping("/unblock_{name}")
	public String unblock_candidate(@PathVariable("name") String name, HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("3"))
			return "unauthorized";
		if(name.equals(""))
			return "404error";
		BlockId blockid=new BlockId(name,session.getAttribute("name").toString());
		BlockCandidate candidate=new BlockCandidate();
		candidate.setBlockid(blockid);
		this.employerService.unblock(candidate);
		return "redirect:/candidate_management";
	}
	
	@RequestMapping("/unlock_{id}_{name}")
	public String unlock_candidate(@PathVariable("id") int id,@PathVariable("name") String name, HttpSession session)
	{
		if(session.getAttribute("name")==null)
			return "redirect:/";
		if(!session.getAttribute("roleId").toString().equals("3"))
			return "redirect:/";
		JobId jobid=new JobId();
		jobid.setApplicant_username(name);
		jobid.setJob_id(id);
		LockCandidate candidate=new LockCandidate();
		candidate.setJobid(jobid);
		boolean status=this.employerService.unlock(candidate,session.getAttribute("name").toString());
		if(status)
			return "redirect:/candidate_management";
		else
			return "unauthorized";
	}
	
	@RequestMapping("/delete_{id}")
	public ModelAndView delete(@PathVariable("id") int id,HttpSession session,final RedirectAttributes redirectAttributes)
	{
		if(session.getAttribute("name")==null)
			return new ModelAndView("redirect:/");
		if(!session.getAttribute("roleId").toString().equals("3"))
			return new ModelAndView("redirect:/");
		this.employerService.delete(id,session.getAttribute("name").toString());
		redirectAttributes.addFlashAttribute("delete","success");
		return new ModelAndView("redirect:/home");
	}
}
