package com.neosoft.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springboot.model.AdminBean;
import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.repository.AdminRepo;
import com.neosoft.springboot.repository.CarRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminrepo;
	
	
	
	@Autowired
	private CarRepo carRepository;
	
	
	public void saveAdmin(AdminBean admin) {
		 adminrepo.save(admin);
	}
	
	public void deleteAdmin(int id)
	{
		adminrepo.deleteById(id);
	}
	
	
	public List<AdminBean> getAllAdmin(){
		return adminrepo.findAll();	
	}
	
	
	
	public List<CarBean> getAllCars(){
		return carRepository.findAll();
	}
	
	public void addCars(CarBean car) {
		carRepository.save(car);
	}
	

	public void deleteCarsById(int id) {
		carRepository.deleteById(id);
		
	}

	public CarBean getCarsById(int id) {
		return carRepository.findById(id).get();
	}

	

	public CarBean updateCars(CarBean car) {
		return carRepository.save(car);
	}

	
	
	
	public boolean adminAuthenticate(String email, String password)
	{
		return AdminRepo.authenticate(email,password);
	}

	public AdminBean findByPassword(String password) {
		return adminrepo.findByPassword(password);
	}
	
	public AdminBean findByEmail(String email) {
		return adminrepo.findByEmail(email);
	}

	public boolean check(String uname, String pass) {
		return adminrepo.Check(uname,pass);
		
	}

	public List<AdminBean> getAdminByEmail(String uname, String pass) {
		return adminrepo.findAll();
	}



	
	
	

}
