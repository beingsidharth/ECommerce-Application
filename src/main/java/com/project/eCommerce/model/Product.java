package com.project.eCommerce.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	private String description;
	private String category;
	private BigDecimal price;
	private boolean productAvailable;
	private int stockQuantity;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy")
	private Date releaseDate;
	
	private String imageName;
	private String imageType;
	
	@Lob 
	private byte[] imageDate;
	
	
	}