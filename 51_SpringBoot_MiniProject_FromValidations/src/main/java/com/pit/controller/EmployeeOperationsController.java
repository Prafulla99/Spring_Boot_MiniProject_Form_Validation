//EmployeeOperationController
package com.pit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pit.model.Employee;
import com.pit.service.IEmployeeMgmtService;
import com.pit.validator.EmployeeValidator;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeOperationsController {

	@Autowired
	IEmployeeMgmtService empService;
	
	@Autowired
	EmployeeValidator empValidator ;

	@GetMapping("/")
	public String showHome() {
		return "welcome";
	}

	@GetMapping("/report")
	public String showReport(Map<String, Object> map) {

		List<Employee> allEmployees = empService.fetchAllEmployees();

		map.put("emplist", allEmployees);

		return "show_report";

	}

	@GetMapping("/register")
	public String showRegisterEmployeeFormPage(@ModelAttribute("emp") Employee emp) {

		return "employee_register_form";
	}

	/*
	  @PostMapping("/register") public String
	  processRegisterEmployeeFromPage(Map<String,Object>
	  map, @ModelAttribute("emp") Employee emp) {
	  
	  String msg = empService.registerEmployee(emp);
	  
	  map.put("resultMsg", msg);
	  
	  List<Employee> list = empService.fetchAllEmployees(); map.put("empsinfo",
	  list);
	  
	  return "show_report";
	  
	  }
	 
  */
	/*
	  // Using PRG Pattern To Solve Double posting problem
	  
	  @PostMapping("/register") public String
	  processRegisterEmployeeFromPage(Map<String,Object>
	  map, @ModelAttribute("emp") Employee emp) {
	  
	  String msgg = empService.registerEmployee(emp);
	  
	  map.put("resultMsg", msgg);
	  
	  
	  
	  return "redirect:report";
	  
	  
	  }
	*/ 

	// Using PRG Pattern + FlashAttribute To Solve Double posting problem

	/*
	  @PostMapping("/register") 
	  public String processRegisterEmployeeFromPage(RedirectAttributes attrs, @ModelAttribute("emp")Employee emp,BindingResult errors) {
	  
		// use validator
		  if (empValidator.supports(emp.getClass())) {
				empValidator.validate(emp, errors);

				if (errors.hasErrors()) {
					return "employee_register_form";
				}
			}
		  
	  String msgg = empService.registerEmployee(emp);
	  attrs.addFlashAttribute("resultMsg",msgg);
	  
	  return "redirect:report"; 
	  
	  }
	 */

	// Using PRG Pattern + Session Attribute To Solve Double posting problem

	
	  @PostMapping("/register") 
	  public String processRegisterEmployeeFromPage(HttpSession ses, @ModelAttribute("emp")Employee emp, BindingResult errors) {
		  
		  // use validator 
	  if(empValidator.supports(emp.getClass())) { 
	  
	   empValidator.validate(emp,errors);
	  
	  if (errors.hasErrors()) { 
	  return "employee_register_form"; 
	    } 
	  }
	  
	  //application logic error
	  if(empService.isDesgInRejectList(emp.getJob())) {
		  errors.rejectValue("job", "emp.desg.reject");
		  return "employee_register_form";
	  }
	  //use service class
	  String msgg = empService.registerEmployee(emp);
	   ses.setAttribute("resultMsg", msgg);
	  
	  return "redirect:report";
	  
	  }
	 

	@GetMapping("/edit")
	public String showEditPage(@RequestParam("no") int no, @ModelAttribute("emp") Employee emp) {

		Employee emp1 = empService.getEmployeeByNo(no);

		// keep emp1 object data to model class object emp
		BeanUtils.copyProperties(emp1, emp);

		return "employee_edit_form";
	}

	@PostMapping("/edit")
	public String proceesEditFormPage(RedirectAttributes attrs, @ModelAttribute("emp") Employee emp,
			BindingResult errors) {

		// use validator
		if (empValidator.supports(emp.getClass())) {
			empValidator.validate(emp, errors);

			if (errors.hasErrors()) {
				return "employee_edit_form";
			}
		}

		String msg = empService.updatEmployee(emp);
		attrs.addFlashAttribute("resultMsg", msg);

		return "redirect:report";
	}

	@GetMapping("/delete")
	public String deleteEmployee(RedirectAttributes rttrs, @RequestParam("no") int no) {

		String msg = empService.deleteEmployee(no);

		rttrs.addFlashAttribute("resultMsg", msg);

		return "redirect:report";

	}

	@ModelAttribute("dnoList")
	public List<Integer> populateDeptNos() {
		List<Integer> dnList = empService.showAllDeptNos();

		return dnList;

	}

}
