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

import com.product.exception.GenericProductManagementException;
import com.product.model.Product;
import com.product.util.ProductCache;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductCache cache;

	public ProductController() {
		this.cache = ProductCache.getInstance();
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> allProducts = cache.getAllProducts();
		return handleRequest(allProducts, HttpStatus.OK);

	}

	@PostMapping(value = "/addProduct")
	public ResponseEntity<String> addProducts(@RequestBody Product product) {

		Product product1 = cache.getProductById(product.getId());
		if (product1 != null) {
			return handleRequest("product is already available", HttpStatus.BAD_REQUEST);
		}
		cache.addProduct(product1);
		return handleRequest("product added successfully", HttpStatus.OK);
	}

	@GetMapping("/getProduct/{pid}")
	public ResponseEntity<?> getProduct(@PathVariable("pid") int id) {
		Product product = cache.getProductById(id);
		if (product == null) {
			return handleRequest("product id not available", HttpStatus.BAD_REQUEST);
		}
		return handleRequest(product, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		return handleRequest(cache.updateProducts(product), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteProduct(int id) {
		if (cache.getProductById(id) != null) {
			cache.deleteProduct(id);
			return handleRequest("product deleted successfully", HttpStatus.ACCEPTED);
		}
		return handleRequest("product id not available", HttpStatus.BAD_REQUEST);

	}

	public <T> ResponseEntity<T> handleRequest(T msg, HttpStatus status) {
		return new ResponseEntity<>(msg, status);
	}

}