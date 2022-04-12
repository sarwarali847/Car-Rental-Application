package com.neosoft.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springboot.model.CarBean;
import com.neosoft.springboot.model.IssueCarBean;
import com.neosoft.springboot.repository.IssuecarRepo;

@Service
public class IssueCarService {
 
	 @Autowired
	 private IssuecarRepo issuecarrepo;
	 
	 
	 public IssueCarBean getCarsById(int id) {
			return issuecarrepo.findById(id).get();
		}
	 
	 public void issueCar(IssueCarBean issuecarbean)
	 {
		 issuecarrepo.save(issuecarbean);
	 }
	 
	 public List<IssueCarBean> getIssuedCar()
	 {
		 return issuecarrepo.findAll();
	 }
	 
	 
	
	 
	 
	 
	 public void updatecar2(int rent, int callno)
	 {
		 issuecarrepo.updateCar2(rent, callno);
	 }

	public void updatecar1(int rent, int callno) {
		issuecarrepo.updateCar2(rent, callno);
		
	}

	public void updateCustomer1(int cid) {
		issuecarrepo.updatecustomer1(cid);
		
	}

	public void updateCustomer2(int cid) {
		issuecarrepo.updatecustomer2(cid);
		
	}
		
		
	

	
	
	
	
	
}
