package com.example.demo.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.demo.entity.Category;
import com.example.demo.entity.ParentCategory;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ParentCategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryRestController {
	@Autowired
	HttpServletResponse resp;
	@Autowired
	ParentCategoryService parentService;
	@Autowired
	CategoryService categoryService;
	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	@GetMapping("/getall/parentcategory")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<List<ParentCategory>> getAllParentCategory(){
		return  ResponseEntity.ok(parentService.getAll());
	}
	
	
	@PostMapping("/create/parentcategory")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<ParentCategory> createCategory(@RequestBody ParentCategory category) {
		category.setId(generateUUID().toString());
		if (parentService.exitById(category.getId())) {
			category.setId(generateUUID().toString());
		};
		return ResponseEntity.ok(parentService.save(category));
	}
	@PutMapping("/update/parentcategory")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> updateParentCategory(@RequestBody ParentCategory category){
		if(parentService.exitById(category.getId())) {
			return ResponseEntity.ok(parentService.save(category));
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/delete/parentcategory/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteParentCategory(@PathVariable("id")String id) throws IOException{
		if(parentService.exitById(id)) {
			if(parentService.findById(id).getCategory().size()>0) {
				resp.sendError(403, "Không thể xóa danh mục đang có danh mục con");
				return ResponseEntity.badRequest().build();
			}
			parentService.deleteById(id);
			return ResponseEntity.ok(null);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("/get/parentcategoryenable")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<List<ParentCategory>> getAllParentEnableTrue(){
		return ResponseEntity.ok(parentService.findAllByEnableTrue());
	}
	////////////////////category//////////////
	@PostMapping("/create/category")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> createCaterogy(@RequestBody Category category,@RequestParam("id") String idparent){
		category.setId(generateUUID().toString());
		if(categoryService.exitById(category.getId())) {
			category.setId(generateUUID().toString());
		}
		category.setParent(parentService.findById(idparent));
		return ResponseEntity.ok(categoryService.save(category));
	}
	@GetMapping("/getall/category")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<List<Category>> getAllcategory(){
		return ResponseEntity.ok(categoryService.findAll());
	}
	
	@PutMapping("/update/category")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> updateCaterogy(@RequestBody Category category,@RequestParam("id") String idparent){
		category.setParent(parentService.findById(idparent));
		return ResponseEntity.ok(categoryService.save(category));
	}
	@DeleteMapping("/delete/category/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategory(@PathVariable("id")String id) throws IOException{
		try {
			categoryService.deleteById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			resp.sendError(403, "Không thể xóa danh mục đang có sản phẩm");
			return ResponseEntity.badRequest().build();
		}
	}
}
