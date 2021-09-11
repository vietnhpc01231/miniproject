package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.User;

public interface RoleDao extends JpaRepository<Role, String>  {

	Role findByName(Enum name);



}
