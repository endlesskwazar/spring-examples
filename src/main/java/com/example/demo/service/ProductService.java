package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;
import com.example.demo.repository.Repository;

@Service
public class ProductService {
	
	@Autowired
	private Repository<Product, Long> productRepository;
	
	public Iterable<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getById(Long id) {
		return productRepository.getById(id).get();
	}
	
	public Product create(Product product) {
		return productRepository.save(product);
	}
	
	public void removeById(Long id) {
		productRepository.removeById(id);
	}

}
