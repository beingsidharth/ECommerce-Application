package com.project.eCommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.eCommerce.model.Product;
import com.project.eCommerce.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	public List<Product> getProducts(){
		return repo.findAll();
	}

	public Product addProduct(Product product, MultipartFile image) throws IOException {
		
		product.setImageName(image.getOriginalFilename());
		product.setImageType(image.getContentType());
		product.setImageDate(image.getBytes());

		return repo.save(product);
	}

	public Product getProductById(int id) {
		return repo.findById(id).orElse(null);
		
	}
	
	public Product updateProduct(Product product, MultipartFile image) throws IOException {
		product.setImageDate(image.getBytes());
		product.setImageName(image.getOriginalFilename());
		product.setImageType(image.getContentType());
		
		return repo.save(product);
	}
	
	public void deleteProduct(int productId) {
		 repo.deleteById(productId);
	}
	
	public List<Product> searchProducts(String keyword){
		return repo.searchProducts(keyword);
	}

}
