package com.neosoft.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.springboot.model.AdminBean;
import com.neosoft.springboot.model.CarBean;

@Repository
public interface AdminRepo extends JpaRepository<AdminBean, Integer>{

	
		@Query("select a from carrental2.admin a where a.email=:em and a.password=:pass")
		public static boolean authenticate(@Param("em") String email,@Param("pass") String password) {
		return true;
		}

		AdminBean findByEmail(String email);

		AdminBean findByPassword(String password);

		
		@Query("SELECT a from AdminBean a Where  a.email=:e AND password=:p")
	     boolean search(@Param("e") String email, @Param("p")  String password);

		@Query("SELECT a from AdminBean a Where  a.email=:uname AND a.password=:pass")
		boolean Check(String uname, String pass);

		AdminBean findByEmailAndPassword(String email, String password);

		
		

		
		
		
			
			


	
	


}
