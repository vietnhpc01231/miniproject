package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable  {
	@Id
	private String id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private RoleName name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<Role_User> roles;
	@Override
	public String toString() {
		return "";
	}
}
