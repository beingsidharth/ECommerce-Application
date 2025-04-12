package com.project.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.eCommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p from Product p WHERE "+ 
			"LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))OR "+
			"LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	public List<Product> searchProducts(String keyword);

}
