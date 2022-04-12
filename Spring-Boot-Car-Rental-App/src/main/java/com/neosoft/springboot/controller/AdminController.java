package com.neosoft.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.springboot.model.AdminBean;
import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.model.CustomerBean;
import com.neosoft.springboot.service.AdminService;
import com.neosoft.springboot.service.CarService;
import com.neosoft.springboot.service.CustomerService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	
	@Autowired
	CustomerService customerservice2;
	
	@Autowired
	CarService carservice2;
	
	
	
	@GetMapping("/admins")
	public String listAdmin(Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		
		model.addAttribute("admin",adminservice.getAllAdmin());
		return "viewadmin";
		
	}
	
	@GetMapping("/admins/new")
	public String createAdminForm(Model model) {
		
		
		AdminBean admins = new AdminBean();
		model.addAttribute("admin", admins);
		
		return "addadminform2";
	}
	
	@PostMapping("/admins")
	public String saveAdmin(@ModelAttribute("admin") AdminBean admins) {
		
		
		adminservice.saveAdmin(admins);
		return "redirect:/home";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteAdmin(@PathVariable int id,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		
		
		adminservice.deleteAdmin(id);
		
		return "redirect:/admins";
	}
	
	//add customer
	
	@GetMapping("/customers2/new")
	public String createCustomerForm2(Model model) {
		
		
		CustomerBean customer = new CustomerBean();
		model.addAttribute("customer2", customer);
		
		return "addcustomerform2";
	}
	
	@PostMapping("/customers2")
	public String saveCustomers(@ModelAttribute("customer2") CustomerBean customer) {
		
		customerservice2.addCustomers(customer);
		
		return "redirect:/home";
	}
	
	
	//view car
	@GetMapping("/cars2")
	public String listCars2(Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		
		//model.addAttribute("car2",carservice2.getAllCars());
		
		return findPaginated(1,model);
	}
	
	//add car

	@GetMapping("/cars2/new")
	public String createCarsForm2(Model model,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		CarBean car = new CarBean();
		model.addAttribute("car2", car);
		
		return "add_car2";
	}
	
	//save car
	
	@PostMapping("/cars2")
	public String saveCar2(@ModelAttribute("car2") CarBean car,HttpSession session) {
		
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		carservice2.addCars(car);
		
		return "redirect:/cars2";
	}
	
	//edit car
	@GetMapping("/cars2/edit/{id}")
	public String editCarForm2(@PathVariable int id, Model model,HttpSession session) {
		
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		model.addAttribute("car2",carservice2.getCarsById(id));
		
		return "edit_car2";
	}
	
	//update car
	@PostMapping("/cars2/{id}")
	public String updateCar2(@PathVariable int id, 
			@ModelAttribute("car") CarBean car,Model model,HttpSession session) {
		
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		
		CarBean existingCar = carservice2.getCarsById(id);
		existingCar.setCallno(car.getCallno());
		existingCar.setCar_name(car.getCar_name());
		existingCar.setCompany_name(car.getCompany_name());
		existingCar.setModel(car.getModel());
		existingCar.setQuantity(car.getQuantity());
				
		carservice2.updateCars(existingCar);
		
		return "redirect:/cars2";
	}
	
	
	//delete car
	
	@GetMapping("/deletecars2/{id}")
	public String deleteCars2(@PathVariable int id,HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
		
		carservice2.deleteCarsById(id);
		
		return "redirect:/cars2";
	}
	
	
	
	 //Check for Credentials
	
	@GetMapping("/AdminSection")
	 public String showLogin(HttpSession session) {
		if (session.getAttribute("email")==null) {
			return "redirect:/home";
		}
	  return "admins";
	 }
	  
	//Check for Credentials
	 @PostMapping("/AdminSection")
	 public String login(@ModelAttribute(name="AdminLoginForm") AdminBean login, Model m,HttpServletRequest request) {
	  
		
	  String uname = login.getEmail();
	  String pass = login.getPassword();
	  
	  
	  List<AdminBean> adminBean=adminservice.getAdminByEmail(uname,pass);
	  boolean a=false;
	  
	  for(AdminBean list: adminBean)
	  {
		  if(list.getEmail().equals(uname) && list.getPassword().equals(pass))
     		  a=true;  
	  }
	  //String e=adminBean.getEmail();
	  //String p=adminBean.getPassword();
	  
	  if(a)
	  {
	   m.addAttribute("uname", uname);
	   m.addAttribute("pass", pass);
	   request.getSession().setAttribute("email", uname);
	   return "admins";
	  }
	  else {
	  m.addAttribute("error", "Incorrect Username & Password");
	  System.out.println("incorrect password");
	  return "index";
	  }
	  
	 }
	 
	 
	 //To invalidate session
	 
	 @GetMapping("/invalidate/session")
		public String destroySession(HttpServletRequest request) {
			request.getSession().invalidate();
			return "redirect:/home";
		}	
	 
	 
	 
	 //Pagination
	 @GetMapping("/page2/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				Model model) {
			int pageSize = 5;
			
			Page<CarBean> page = carservice2.findPaginated(pageNo, pageSize);
			List<CarBean> listCars2 = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("listCars2", listCars2);
			return "cars2";
		}
		
		
		
}
	 
	
	
	
	

