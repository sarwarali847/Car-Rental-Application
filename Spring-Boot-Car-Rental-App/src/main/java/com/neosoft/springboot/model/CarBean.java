package com.neosoft.springboot.model;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class CarBean{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int callno;

	private String car_name;
	
	
	private String company_name;
	

	private String model;
	
	
	private int quantity;
	
	
	private int rented;

	
	
}

