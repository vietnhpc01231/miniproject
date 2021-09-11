package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "role_user", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"id_user", "id_role"})
})
@AllArgsConstructor
@NoArgsConstructor
public class Role_User implements Serializable  {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@ManyToOne @JoinColumn(name = "id_user")
	private User user;

	@ManyToOne  @JoinColumn(name = "id_role")
	private Role role;
	
	@Override
	public String toString() {
		return "";
	}
}
