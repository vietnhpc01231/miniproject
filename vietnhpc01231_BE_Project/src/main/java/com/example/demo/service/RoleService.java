package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;

public interface RoleService {

	Role findByName(Enum name);

	void reateRole(User user, RoleName roleUser);

}
