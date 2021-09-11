package com.example.demo.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ParenCategoryDao;
import com.example.demo.entity.ParentCategory;
import com.example.demo.service.ParentCategoryService;

@Service
public class ParentCategoryIpm implements ParentCategoryService{
	@Autowired
	ParenCategoryDao dao;
	@Override
	public boolean exitById(String id) {
		return dao.existsById(id);
	}
	@Override
	public ParentCategory save(ParentCategory category) {
		return dao.save(category);
	}
	@Override
	public List<ParentCategory> getAll() {
		return dao.findAll();
	}
	@Override
	public ParentCategory findById(String id) {
		return dao.findById(id).get();
	}
	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}
	@Override
	public List<ParentCategory> findAllByEnableTrue() {
		return dao.findAllByEnableTrue();
	}

}
