package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ParentCategory;

public interface ParentCategoryService {

	boolean exitById(String id);

	ParentCategory save(ParentCategory category);

	List<ParentCategory> getAll();

	ParentCategory findById(String id);

	void deleteById(String id);

	List<ParentCategory> findAllByEnableTrue();

}
