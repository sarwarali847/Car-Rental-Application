package com.neosoft.springboot.controller;

import java.util.List;

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
import com.neosoft.springboot.service.CarService;





@Controller
public class CarController {
	
	@Autowired
	private CarService carService;
	
	
	//Displaying all cars
	@GetMapping("/cars")
	public String listCars(Model model,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		//model.addAttribute("car",carService.getAllCars());
		
		return findPaginated(1,model);
	}
	
	
	@GetMapping("/cars/new")
	public String createCarsForm(Model model,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		
		CarBean car = new CarBean();
		model.addAttribute("car", car);
		
		return "add_car";
	}
	
	@PostMapping("/cars")
	public String saveCar(@ModelAttribute("car") CarBean car,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		carService.addCars(car);
		
		return "redirect:/cars";
	}

	
	
	@GetMapping("/cars/edit/{id}")
	public String editCarForm(@PathVariable int id, Model model,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		model.addAttribute("car",carService.getCarsById(id));
		
		return "edit_car";
	}
	
	
	@PostMapping("/cars/{id}")
	public String updateCar(@PathVariable int id, 
			@ModelAttribute("car") CarBean car,Model model,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		CarBean existingCar = carService.getCarsById(id);
		existingCar.setCallno(car.getCallno());
		existingCar.setCar_name(car.getCar_name());
		existingCar.setCompany_name(car.getCompany_name());
		existingCar.setModel(car.getModel());
		existingCar.setQuantity(car.getQuantity());
				
		carService.updateCars(existingCar);
		
		return "redirect:/cars";
	}
//	
//	
//	
//	
	@GetMapping("/deletecars/{id}")
	public String deleteCars(@PathVariable int id,HttpSession session2) {
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		carService.deleteCarsById(id);
		
		return "redirect:/cars";
	}

	
	//searching
	
	@GetMapping("/searchcar")
	public String searchCar(Model model, @Param("keyword") String keyword,HttpSession session2) {
		
		if (session2.getAttribute("email2")==null) {
			return "redirect:/home";
		}
		List<CarBean> listProducts = carService.listAll(keyword);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("keyword", keyword);
		return "getcar";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			Model model) {
		int pageSize = 5;
		
		Page<CarBean> page = carService.findPaginated(pageNo, pageSize);
		List<CarBean> listCars = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		
		model.addAttribute("listCars", listCars);
		return "cars";
	}
	
	
	
	
	

}
