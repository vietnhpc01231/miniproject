package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="product")
public class Product implements Serializable{
	@Id
	private String id;
	private String name;
	private boolean enable;
	private Double price=0.0;
	private int quantity =0;
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@Override
	public String toString() {
		return "";
	}
}
