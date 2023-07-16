package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.CategoryException;
import com.dmart.model.Category;
import com.dmart.service.CategoryService;


@RestController
@RequestMapping("/dmart")
public class CategoryController {
	
	@Autowired
	private CategoryService cService;
	
	//API to add product Category
	
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category) throws CategoryException{
		
		Category newCategory = cService.addCategory(category);
		
		return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
		
	}
	
	//API to update product Category
	
	@PutMapping("/updateCategory")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category) throws CategoryException{
		
		Category newCategory = cService.updateCategory(category);
		
		return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
		
	}
	
	//API to delete product Category
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<Category> deleteCategoryHandler(@PathVariable Long id) throws CategoryException{
		
		Category newCategory = cService.deleteCategory(id);
		
		return new ResponseEntity<Category>(newCategory,HttpStatus.OK);
		
	}
	
	//API to get product Category details by their respective id
	
	@GetMapping("/getCategoryById/{id}")
	public ResponseEntity<Category> getCategoryByIdHandler(@PathVariable Long id) throws CategoryException{
		
		Category newCategory = cService.getCategoryById(id);
		
		return new ResponseEntity<Category>(newCategory,HttpStatus.OK);
		
	}
	
	//API to view all the Product Categories Available
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategoryHandler() throws CategoryException{
		
		List<Category> newCategory = cService.getAllCategory();
		
		return new ResponseEntity<List<Category>>(newCategory,HttpStatus.OK);
		
	}
	
	

}
