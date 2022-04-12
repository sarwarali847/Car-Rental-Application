package com.neosoft.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	
	private String email;
	
	
	private String password;
	
	
	@Column(unique = true)
	private long mobile;
	
	
	
}
