package com.example.demo.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductRestController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	
	@GetMapping("/get/product")
	public ResponseEntity<List<Product>> getListProduct(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	@PostMapping("/create/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product ,@RequestParam("id")String categoy){
		System.err.println(product.getName()+"name" + categoy);
		product.setId(generateUUID().toString());
		if(productService.exitByID(product.getId())) {
			product.setId(generateUUID().toString());
		}
		//System.err.println(categoryService.findById(categoy).getId());
		product.setCategory(categoryService.findById(categoy));
		return ResponseEntity.ok(productService.save(product));
	}
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id")String id){
		productService.delete(id);
		return ResponseEntity.ok(null);
	}
	@PutMapping("/update/product")
	public ResponseEntity<Product> updateproduct(@RequestBody Product product ,@RequestParam("id")String categoy){
		System.err.println(product.getId());
		product.setCategory(categoryService.findById(categoy));
		return ResponseEntity.ok(productService.save(product));
	}
}
