package com.example.market.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findById(long id);

	public List<Product> findByStuff(String stuff);
}
