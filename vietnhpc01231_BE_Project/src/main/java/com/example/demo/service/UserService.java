package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;

public interface UserService {
	Optional<User> findByUsername(String username);

	User save(User user);

	boolean exitByUsername(String username);

	List<User> findAll();

	void delete(User user);

	boolean isPM(String remoteUser);

}
