package com.example.demo.repository;

import java.util.Optional;

public interface Repository<T, ID> {

	T save(T entity);
	Optional<T> getById(ID id);
	Iterable<T> findAll();
	void removeById(ID id);
	
}
