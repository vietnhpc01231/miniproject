package com.example.demo.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
@Service
public class UserIpm implements UserService{
	@Autowired
	UserDao dao;
	
	@Override
	public Optional<User> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return dao.save(user);
	}

	@Override
	public boolean exitByUsername(String username) {
		return dao.existsById(username);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(User user) {
		 dao.delete(user);
	}

	@Override
	public boolean isPM(String username) {
		List<Role_User> roles = this.findByUsername(username).get().getRoles();
		for (Role_User role_User : roles) {
			if (role_User.getRole().getName().equals(RoleName.ROLE_PM)) {
				return true;
			}
		}
		return false;
	}

}
