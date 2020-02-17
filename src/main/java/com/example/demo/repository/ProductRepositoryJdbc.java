package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
@Profile("jdbc")
public class ProductRepositoryJdbc implements com.example.demo.repository.Repository<Product, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Product save(Product entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(
					"insert into products(title, description, price) values (?, ?, ?)", new String[] { "id" });
			ps.setString(1, entity.getTitle());
			ps.setString(2, entity.getDescription());
			ps.setBigDecimal(3, entity.getPrice());
			return ps;
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity;
	}

	@Override
	public Optional<Product> getById(Long id) {
		return jdbcTemplate.queryForObject("select * from products where id = ?", new Object[] { id },
				(res, rowNum) -> Optional.of(new Product(res.getLong("id"), res.getString("title"),
						res.getString("description"), res.getBigDecimal("price"))));
	}

	@Override
	public Iterable<Product> findAll() {
		return jdbcTemplate.query("select * from products;", (rs, rowNum) -> new Product(rs.getLong("id"),
				rs.getString("title"), rs.getString("description"), rs.getBigDecimal("price")));
	}

	@Override
	public void removeById(Long id) {
		jdbcTemplate.update("delete products where id = ?", id);
	}

}
