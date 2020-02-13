package com.example.demo.repository;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Product;
import com.example.demo.dummydata.DummyData;

@Repository
public class ProductRepository implements com.example.demo.repository.Repository<Product, Long> {
	
	public void removeById(Long id) {
		Product productToDelete = DummyData.getInstance().getProducts().stream()
				.filter(p -> p.getId().equals(id))
				.findAny()
				.get();
		DummyData.getInstance().getProducts().remove(productToDelete);
	}

	@Override
	public Product save(Product entity) {
		Product maxById = DummyData.getInstance().getProducts()
			      .stream()
			      .max(Comparator.comparing(Product::getId))
			      .orElseThrow(NoSuchElementException::new);
		entity.setId(maxById.getId() + 1);
		DummyData.getInstance().getProducts().add(entity);
		return entity;
	}

	@Override
	public Optional<Product> getById(Long id) {
		return DummyData.getInstance().getProducts().stream()
				.filter(p -> p.getId().equals(id))
				.findAny();
	}

	@Override
	public Iterable<Product> findAll() {
		return DummyData.getInstance().getProducts();
	}
	
}
