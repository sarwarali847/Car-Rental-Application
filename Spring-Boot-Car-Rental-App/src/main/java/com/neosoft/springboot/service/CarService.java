package com.neosoft.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.repository.CarRepo;




@Service
public class CarService {
	
	@Autowired
	private CarRepo carRepository;
	
	public List<CarBean> getAllCars(){
		return carRepository.findAll();
	}
	
	public void addCars(CarBean car) {
		carRepository.save(car);
	}
	

	public void deleteCarsById(int id) {
		carRepository.deleteById(id);
		
	}

	public CarBean getCarsById(Integer id) {
		return carRepository.findById(id).get();
	}
	
	

	

	public CarBean updateCars(CarBean car) {
		return carRepository.save(car);
	}

	public CarBean getCarsByCallno(int callno) {
		return carRepository.findByCallno(callno);
	}

	public List<CarBean> findByCarName(String car_name) {
		return carRepository.findByCarname(car_name);
	}


	
	public List<CarBean> listAll(String keyword) {
        if (keyword != null) {
            return carRepository.search(keyword);
        }
        else
        return carRepository.findAll();
    }
	
	public Page<CarBean> findPaginated(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.carRepository.findAll(pageable);
	}

	
}
