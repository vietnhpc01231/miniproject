package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable  {
	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String phone;
	private Boolean gender;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Role_User> roles;
	
	@Override
	public String toString() {
		return "";
	}
	
}
