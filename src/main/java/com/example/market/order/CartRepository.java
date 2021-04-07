package com.example.market.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.market.product.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//	public List<Cart> findByProduct(Product product);
	public Cart findByProduct(Product product);

	public List<Cart> findByUserId(String userId);
}