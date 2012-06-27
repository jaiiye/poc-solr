package com.credera.java.service;

import java.util.List;

import com.credera.java.domain.Product;

public interface ProductService {
	public void saveProduct(Product product);
	public List<Product> getAllProducts();
	public Product getProduct(Long id);

}
