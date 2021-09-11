package com.example.demo.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductIpm implements ProductService {
	@Autowired
	ProductDao dao;
	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}
	@Override
	public boolean exitByID(String id) {
		return dao.existsById(id);
	}
	@Override
	public Product save(Product product) {		
		return dao.save(product);
	}
	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

}
