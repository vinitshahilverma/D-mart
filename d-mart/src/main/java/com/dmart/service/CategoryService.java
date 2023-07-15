package com.dmart.service;

import java.util.List;

import com.dmart.exception.CategoryException;
import com.dmart.model.Category;

public interface CategoryService {
	
	public Category addCategory(Category category)throws CategoryException;
	
	public Category deleteCategory(Long catId)throws CategoryException;
	
	public Category updateCategory(Category category)throws CategoryException;
	
	public List<Category> getAllCategory()throws CategoryException;
	
	public Category getCategoryById(Long id)throws CategoryException;

}
