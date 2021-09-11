package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Category;

public interface CategoryService {

	boolean exitById(String id);

	Category save(Category category);

	List<Category> findAll();

	void deleteById(String id);

	Category findById(String categoy);

}
