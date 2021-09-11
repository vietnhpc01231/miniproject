package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ParentCategory;

public interface ParenCategoryDao extends JpaRepository<ParentCategory, String> {

	List<ParentCategory> findAllByEnableTrue();

}
