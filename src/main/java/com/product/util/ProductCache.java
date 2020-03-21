package com.product.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		throw new RuntimeException();
		
	}

	/*
	 * delete product based on id
	 */
	public void deleteProduct(int id) {
		productData.remove(id);
	}

}
