package com.example.demo.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Role_UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;
import com.example.demo.service.Role_UserService;

@Service
public class Role_UserIpm implements Role_UserService {
	@Autowired
	Role_UserDao dao;

	@Override
	public Role_User save(Role_User roles) {
		return dao.save(roles);
	}

	@Override
	public void delete(Role_User role_User) {
		dao.delete(role_User);

	}

	@Override
	public Role findByUserAndRole(User user, Role findByName) {
		return dao.findByUserAndRole(user, findByName);
	}

	@Override
	public List<Role_User> findByUser(User user) {
		return dao.findByUser(user);
	}

	@Override
	public void deleteRole(User user) {
		List<Role_User> roles = dao.findByUser(user);
		System.err.println(roles);
		if (!roles.isEmpty()) {
			for (Role_User role_User : roles) {
				dao.delete(role_User);
			}
		}
	}

}
