package com.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.util.ProductCache;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductCache cache;

	public ProductController() {
		this.cache = ProductCache.getInstance();
	}

	@GetMapping(value = "/getAllProducts")
	public List<Product> getAllProducts() {
		List<Product> allProducts = cache.getAllProducts();
		return allProducts;
	}

	@PostMapping(value = "/addProduct")
	public void addProducts(@RequestBody Product product) {

		cache.addProduct(product);
	}

	@GetMapping("/getProduct/{pid}")
	public Product getProduct(@PathVariable("pid") int id) {
		Product productById = cache.getProductById(id);
		return productById;

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		try {
			Product updateProduct = cache.updateProducts(product);
			return new ResponseEntity<>(updateProduct, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("product id not available", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteProduct(int id) {
		if (cache.getProductById(id) != null) {
			cache.deleteProduct(id);
			return new ResponseEntity<>("product deleted successfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("product id not available", HttpStatus.BAD_REQUEST);
		
		
	}

}