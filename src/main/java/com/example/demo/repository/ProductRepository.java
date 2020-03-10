package com.example.demo.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	public Page<Product> findByPriceGreaterThanAndPriceLessThan(BigDecimal leftPrice,
			BigDecimal rightPrice, Pageable pageable);
	
}
