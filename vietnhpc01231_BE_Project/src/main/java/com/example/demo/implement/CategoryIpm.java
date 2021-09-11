package com.example.demo.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@Service
public class CategoryIpm implements CategoryService{
	@Autowired
	CategoryDao dao;
	@Override
	public boolean exitById(String id) {
	return dao.existsById(id);
	}
	@Override
	public Category save(Category category) {
		return dao.save(category);
	}
	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}
	@Override
	public void deleteById(String id) {
	dao.deleteById(id);
	}
	@Override
	public Category findById(String categoy) {
		return dao.findById(categoy).get();
	}

}
