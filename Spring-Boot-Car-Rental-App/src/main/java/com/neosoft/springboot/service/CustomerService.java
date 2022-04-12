package com.neosoft.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neosoft.springboot.model.CustomerBean;
import com.neosoft.springboot.repository.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerrepo;
	
	public List<CustomerBean> getAllCustomers(){
		return customerrepo.findAll();
	}
	
	public void addCustomers(CustomerBean customer) {
		customerrepo.save(customer);
	}

	public void deleteCustomerById(int id) {
		customerrepo.deleteById(id);
		
	}

	public CustomerBean getCustomerById(int id) {
		return customerrepo.findById(id).get();
	}

	

	public CustomerBean updateCustomer(CustomerBean acc) {
		return customerrepo.save(acc);
	}

	public List<CustomerBean> listAll(String keyword) {
		 if (keyword != null) {
	            return customerrepo.search(keyword);
	        }
	        return customerrepo.findAll();
	}

	public List<CustomerBean> getCustomerByEmail(String uname, String pass) {
		return customerrepo.findAll();
	}

	public Page<CustomerBean> findPaginated2(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.customerrepo.findAll(pageable);
	}
}


