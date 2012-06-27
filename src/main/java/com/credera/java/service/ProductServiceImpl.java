package com.credera.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credera.java.dao.ProductDao;
import com.credera.java.domain.Product;

@Service("productService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public ProductServiceImpl() {}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public Product getProduct(Long id) {
		return productDao.getProduct(id);
	}
		
}
