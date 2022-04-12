package com.neosoft.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.model.CustomerBean;
import com.neosoft.springboot.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired CustomerService customerservice;
	
	@GetMapping("/admincrausal")
	public String adminCaraousl(HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		return "admins";
	}
	
	@GetMapping("/customercrausal")
	public String carousal(HttpSession session2){
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}

		return "customers";
	}
	
	
	
	
	@GetMapping("/customers")
	public String listCustomer(Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		//model.addAttribute("customer",customerservice.getAllCustomers());
		
		return findPaginated3(1, model);
	}
	
	@GetMapping("/customers/new")
	public String createCustomerForm(Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		CustomerBean customer = new CustomerBean();
		model.addAttribute("customer", customer);
		
		return "add_customer";
	}
	
	@PostMapping("/customers")
	public String saveCustomers(@ModelAttribute("customer") CustomerBean customer,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		customerservice.addCustomers(customer);
		
		return "redirect:/customers";
	}
	
	
	@GetMapping("/customers/edit/{id}")
	public String editCustomersForm(@PathVariable int id, Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		model.addAttribute("customer",customerservice.getCustomerById(id));
		
		return "update_customer";
	}
	
	
	@PostMapping("/customers/{id}")
	public String updateCustomers(@PathVariable int id, 
			@ModelAttribute("customer") CustomerBean c,Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		CustomerBean existingCustomer = customerservice.getCustomerById(id);
		existingCustomer.setName(c.getName());
		existingCustomer.setEmail(c.getEmail());
		existingCustomer.setPassword(c.getPassword());
		existingCustomer.setMobile(c.getMobile());
				
		customerservice.updateCustomer(existingCustomer);
		
		return "redirect:/customers";
	}
	
	
	
	
	@GetMapping("/customers/delete/{id}")
	public String deleteCustomers(@PathVariable int id,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		customerservice.deleteCustomerById(id);
		
		return "redirect:/customers";
	}
	
	@GetMapping("/searchcustomer")
	public String searchCar(Model model, @Param("keyword") String keyword,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		List<CustomerBean> listCustomers = customerservice.listAll(keyword);
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("keyword", keyword);
		
		return "getcustomer";
	}
	
	
	@GetMapping("/CustomerSection")
	 public String showLogin(HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
	  return "customers";
	 }
	
	
	//Check for Credentials
		 @PostMapping("/CustomerSection")
		 public String login(@ModelAttribute(name="CustomerLoginForm") CustomerBean login2, Model m2,HttpServletRequest request2) {
		  String email = login2.getEmail();
		  String password = login2.getPassword();
		  
		  List<CustomerBean> customerBean=customerservice.getCustomerByEmail(email,password);
		  boolean a=false;
		  
		  for(CustomerBean list: customerBean)
		  {
			  if(list.getEmail().equals(email) && list.getPassword().equals(password))
	     		  a=true;  
		  }
		  //String e=adminBean.getEmail();
		  //String p=adminBean.getPassword();
		  
		  if(a)
		  {
		   m2.addAttribute("email", email);
		   m2.addAttribute("password", password);
		   request2.getSession().setAttribute("email2", email);
		   return "customers";
		  }
		  
		  else {
		  m2.addAttribute("error2", "Incorrect Username & Password");
		  System.out.println("incorrect password");
		  return "index";
		  }
		  
		 }
		 
		 
		 
		 //To invalidate session
		 
		 @GetMapping("/invalidate/session2")
			public String destroySession(HttpServletRequest request2) {
				request2.getSession().invalidate();
				return "redirect:/home";
			}	
		 
		 
		 @GetMapping("/page3/{pageNo}")
			public String findPaginated3(@PathVariable (value = "pageNo") int pageNo, 
					Model model) {
				int pageSize = 5;
				
				Page<CustomerBean> page = customerservice.findPaginated2(pageNo, pageSize);
				List<CustomerBean> listCustomer = page.getContent();
				
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("listCustomer", listCustomer);
				return "viewcustomer";
			}

}
