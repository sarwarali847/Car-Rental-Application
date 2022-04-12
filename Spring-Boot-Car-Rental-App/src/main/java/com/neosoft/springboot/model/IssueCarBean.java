package com.neosoft.springboot.model;

import java.sql.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issuecar")
public class IssueCarBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int callno;
	
	private int customerid;
	
	private String customername;
	
	private long customermobile;
	
	private Date rentaldate;
	
	private String returnstatus;
	
	
	
	
	

}
