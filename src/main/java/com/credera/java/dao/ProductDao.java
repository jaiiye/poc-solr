package com.credera.java.dao;

import java.util.List;

import com.credera.java.domain.Product;

public interface ProductDao {

	public void saveProduct(Product product);
	public Product getProduct(Long id);
	public List<Product> getAllProducts();

}

