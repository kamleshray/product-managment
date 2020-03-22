package com.product.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.product.exception.ExceptionResponse;
import com.product.exception.GenericProductManagementException;
import com.product.model.Product;

public class ProductCache {
	private static ProductCache instance;
	private Map<Integer, Product> productData = new HashMap<Integer, Product>();

	private ProductCache() {

	}

	public static ProductCache getInstance() {
		if (instance == null) {
			instance = new ProductCache();
		}
		return instance;

	}

	/*
	 * add product
	 */
	public void addProduct(Product product) {
		// add product object based on product id
		productData.put(product.getId(), product);// DB call
	}

	/*
	 * fetch product details by id
	 */
	public Product getProductById(int id) {
		Product product = productData.get(id);
		return product;
	}

	/*
	 * fetch list of products
	 */
	public List<Product> getAllProducts() {

		Collection<Product> values = productData.values();
		return new ArrayList<Product>(values);
	}

	/*
	 * update product based on id
	 */

	public Product updateProducts(Product product) {
		Product existingProduct = productData.get(product.getId());
		if(existingProduct!=null) {
			if (product.getPrice() != null) {
				existingProduct.setPrice(product.getPrice());
				productData.put(existingProduct.getId(), existingProduct);
			}
			return existingProduct;
		}
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage("product id not available");
		exceptionResponse.setHttpstatus(HttpStatus.BAD_REQUEST);
		
		throw new GenericProductManagementException(exceptionResponse);
		
	}

	/*
	 * delete product based on id
	 */
	public void deleteProduct(int id) {
		productData.remove(id);
	}

}
