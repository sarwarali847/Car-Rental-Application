package com.neosoft.springboot.controller;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.model.IssueCarBean;
import com.neosoft.springboot.service.CarService;
import com.neosoft.springboot.service.IssueCarService;

@Controller
public class IsuueCarController {
	
	
	
	@Autowired
	private IssueCarService issuecarservice;
	@Autowired
	private CarService carservice;
	
	
	 
	@GetMapping("/getissuedcars")
       public String issuedCars(Model model,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		model.addAttribute("car",issuecarservice.getIssuedCar());
		
		return "issuedcar";
	}

	@GetMapping("/getissuedcars/new")
	public String issueCarForm(Model model,HttpSession session2) {
		
		
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		IssueCarBean car = new IssueCarBean();
		model.addAttribute("car", car);
		
		return "issuecar";
	}
	
	
	
	
	@PostMapping("/getissuedcars")
	public String saveIssueCar(@ModelAttribute("car") IssueCarBean car,HttpSession session2) {
		 
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
		car.setRentaldate(currentDate);
		car.setReturnstatus("no");
		int cid=car.getCustomerid();
		issuecarservice.updateCustomer1(cid);
		
		issuecarservice.issueCar(car);
		CarBean carBean=carservice.getCarsByCallno(car.getCallno());
		int rent=carBean.getRented();
		issuecarservice.updatecar1(rent+1,car.getCallno());
		return "redirect:/getissuedcars";
	}
	

	
	@GetMapping("/returncar/{id}")
	public String returncarform(Model model,HttpSession session2) {
		
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		IssueCarBean car=new IssueCarBean();
		model.addAttribute("car",car);
		return "returncar";
	}
	
	@PostMapping("/updateissuedcars")
	public String updateIssueCar(@ModelAttribute("car") IssueCarBean car,HttpSession session2) {
		
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		CarBean carBean=carservice.getCarsByCallno(car.getCallno());
		int rent=carBean.getRented();
		issuecarservice.updatecar2(rent-1,car.getCallno());
		
		int cid=car.getCustomerid();
		issuecarservice.updateCustomer2(cid);
	    
		return "redirect:/getissuedcars";
	}
	
	
	
	
	

}
