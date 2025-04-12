package com.project.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.eCommerce.model.Product;
import com.project.eCommerce.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
	}
	
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		 Product product = service.getProductById(id);
		 if(product != null) 
			 return new ResponseEntity<>(product, HttpStatus.OK);
		 else 
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,
										@RequestPart MultipartFile imageFile){
		
		try {
			
	        // Convert JSON string to Product object
	   /*     ObjectMapper objectMapper = new ObjectMapper();
	        Product product1 = objectMapper.readValue(product, Product.class);  */
			
			
		Product response = service.addProduct(product, imageFile);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
		
		Product product = service.getProductById(productId);
		byte[] imageDate = product.getImageDate();
		
		return ResponseEntity.ok()
							 .contentType(MediaType.valueOf(product.getImageType()))
							 .body(imageDate);
	}
	
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
		
		try {
			 Product updated = service.updateProduct(product, imageFile);
			 return new ResponseEntity<>(updated, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product response = service.getProductById(id);
		
		if(response != null) {
			service.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
	}
	
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		List<Product> products = service.searchProducts(keyword);
			return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
}
