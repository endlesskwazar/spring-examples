package com.example.demo.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
@Profile("hb")
public class ProductRepositoryHb implements com.example.demo.repository.Repository<Product, Long> {

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public Product save(Product entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Optional<Product> getById(Long id) {
		return Optional.of(em.getReference(Product.class, id));
	}

	@Override
	public Iterable<Product> findAll() {
		Query q = em.createQuery("select p from Product p");
		return q.getResultList();
	}

	@Override
	@Transactional
	public void removeById(Long id) {
		em.remove(this.getById(id).get());
	}

}
