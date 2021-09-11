package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {

	List<Product> findAll();

	boolean exitByID(String id);

	Product save(Product product);

	void delete(String id);

}
