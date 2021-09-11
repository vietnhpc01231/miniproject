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
@Table(name="parent_category")
public class ParentCategory implements Serializable{
	@Id
	String id;
	String name;
	Boolean enable;
	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	List<Category> category;
	
	@Override
	public String toString() {
		return "";
	}
}
