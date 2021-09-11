package com.example.demo.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.Role_UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
@Service
public class RoleIpm implements RoleService{
	@Autowired
	RoleDao dao;
	@Autowired 
	Role_UserDao roleuDao;
	@Override
	public Role findByName(Enum name) {
		return dao.findByName(name);
	}
	@Override
	public void reateRole(User user, RoleName roleUser) {
		Role_User roles = new Role_User();
		roles.setUser(user);
		roles.setRole(dao.findByName(roleUser));
		roleuDao.save(roles);
	}

	

}
