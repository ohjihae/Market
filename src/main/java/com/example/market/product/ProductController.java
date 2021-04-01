package com.example.market.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private ProductRepository productRepo;

	@Autowired
	public ProductController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public List<Product> getProduct() {
		return productRepo.findAll();
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product getproductDetail(@PathVariable long id) {
		Product product = productRepo.findById(id);
		return product;
	}

	// GET /product/search/name?keyword=파
	@RequestMapping(value = "/product/search/name", method = RequestMethod.GET)
	public List<Product> getProductsByName(@RequestParam("keyword") String keyword) {
		return productRepo.findByNameContaining(keyword);
	}

	// GET /product/search/stuff?keyword=파
	@RequestMapping(value = "/product/search/stuff", method = RequestMethod.GET)
	public List<Product> getProductsByStuff(@RequestParam("keyword") String keyword) {
		return productRepo.findByStuffContaining(keyword);
	}

	// GET /product/search/category?keyword=채소
	@RequestMapping(value = "/product/search/category", method = RequestMethod.GET)
	public List<Product> getProductsByCategory(@RequestParam("keyword") String keyword) {
		return productRepo.findByCategoryContaining(keyword);
	}
}
