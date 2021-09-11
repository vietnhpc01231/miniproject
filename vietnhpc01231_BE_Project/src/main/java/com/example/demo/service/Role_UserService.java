package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;

public interface Role_UserService {

	Role_User save(Role_User roles);


	void delete(Role_User role_User);

	Role findByUserAndRole(User user, Role findByName);


	List<Role_User> findByUser(User user);


	void deleteRole(User user);

}
