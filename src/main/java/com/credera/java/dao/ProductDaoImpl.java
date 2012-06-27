package com.credera.java.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.credera.java.domain.Product;


@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveProduct(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}

	public Product getProduct(Long id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		return (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class).list();
	}

}
