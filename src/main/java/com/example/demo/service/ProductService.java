package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Iterable<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//added
	public Page<Product> findAllWithPagination(Pageable pageRequest) {
		return productRepository.findAll(pageRequest);
	}
	
	public Product getById(Long id) {
		return productRepository.findById(id).get();
	}
	
	public Product create(Product product) {
		return productRepository.save(product);
	}
	
	public void removeById(Long id) {
		productRepository.deleteById(id);
	}
	
	public Page<Product> getInPriceRange(BigDecimal leftPrice, BigDecimal rightPrice, Pageable pageRequest) {
		return productRepository.findByPriceGreaterThanAndPriceLessThan(leftPrice, rightPrice, pageRequest);
	}

}
