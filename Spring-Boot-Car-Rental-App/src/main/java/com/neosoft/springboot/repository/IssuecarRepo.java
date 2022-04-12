package com.neosoft.springboot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.springboot.model.IssueCarBean;

@Repository

public interface IssuecarRepo extends JpaRepository<IssueCarBean, Integer> {

	
	
	

	
	
	@Transactional
	@Modifying
	@Query("UPDATE IssueCarBean i SET i.returnstatus='yes' WHERE i.customerid=:cid")
	void updateissuecar(String cid);

	
	
	@Transactional
	@Modifying
	@Query("UPDATE CarBean c SET c.rented=:rent WHERE c.callno=:call")
	void updateCar1(int rent, @Param("call") int callno);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE CarBean c SET c.rented=:rent WHERE c.callno=:call")
	void updateCar2(int rent, @Param("call") int callno);

	@Transactional
	@Modifying
	@Query("UPDATE IssueCarBean c SET c.returnstatus='no' WHERE c.customerid=:cid")
	void updatecustomer1(int cid);


	@Transactional
	@Modifying
	@Query("UPDATE IssueCarBean c SET c.returnstatus='yes' WHERE c.customerid=:cid")
	void updatecustomer2(int cid);

	

	

	



	

	
	
	
}
