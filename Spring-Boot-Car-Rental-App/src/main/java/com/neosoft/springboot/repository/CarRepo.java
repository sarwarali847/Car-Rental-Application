package com.neosoft.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.neosoft.springboot.model.CarBean;

@Repository
public interface CarRepo extends JpaRepository<CarBean, Integer>{

	CarBean findByCallno(int callno);

	@Query("SELECT t FROM CarBean t WHERE t.car_name like 'c%'")
	List<CarBean> findByCarname(@Param("c")  String car_name);

	
	@Query("SELECT p FROM CarBean p WHERE p.car_name LIKE %?1% OR p.company_name LIKE %?1%  OR p.model LIKE %?1% OR p.callno LIKE %?1% OR p.quantity LIKE %?1%  ")
	public List<CarBean> search(String keyword);

	


	


	
	//@Query("SELECT t.rented FROM CarBean t WHERE t.callno=:call ")
	//int findByRented(@Param("call") String callno);

}
