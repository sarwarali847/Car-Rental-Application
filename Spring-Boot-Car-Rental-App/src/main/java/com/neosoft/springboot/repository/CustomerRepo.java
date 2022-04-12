package com.neosoft.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.springboot.model.CustomerBean;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerBean, Integer> {

	
	@Query("SELECT p FROM CustomerBean p WHERE p.name LIKE %?1% OR p.email LIKE %?1%  OR p.password LIKE %?1% OR p.mobile LIKE %?1% OR p.id LIKE %?1%")
	public List<CustomerBean> search(String keyword);

}
