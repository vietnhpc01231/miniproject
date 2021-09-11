package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Role;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;

public interface Role_UserDao extends JpaRepository<Role_User, Integer> {
	
	void deleteByUser(User user);

	Role findByUserAndRole(User user, Role findByName);

	List<Role_User> findByUser(User user);
	
}
