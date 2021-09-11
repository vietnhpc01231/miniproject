package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="category")
public class Category   implements Serializable{
	@Id
	private String id;
	private String name;
	private Boolean enable;
	
	@ManyToOne
	@JoinColumn(name = "id_parent")
	private ParentCategory parent;
	
	
	@JsonIgnore
	@OneToMany(mappedBy= "category")
	private List<Product> product;
	
	@Override
	public String toString() {
		return "";
	}
}
