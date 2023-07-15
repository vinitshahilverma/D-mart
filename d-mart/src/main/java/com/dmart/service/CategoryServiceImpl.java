package com.dmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.CategoryException;
import com.dmart.model.Category;
import com.dmart.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo cRepo;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		
		if(category!=null) {
			category = cRepo.save(category);
		}
		
		else throw new CategoryException("Category can't be null");
		
		return category;
		
	}

	@Override
	public Category deleteCategory(Long catId) throws CategoryException {
		
		Category category = cRepo.findById(catId)
				                 .orElseThrow(() -> new CategoryException("No category Found"));
		
		cRepo.delete(category);
		
        return category;
		
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		
		Category newCategory = cRepo.findById(category.getCatId())
                .orElseThrow(() -> new CategoryException("No category Found"));
		
		newCategory = cRepo.save(category);
		
		return newCategory;
		
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		
		List<Category> list = cRepo.findAll();
		
		if(list.size()==0) {
			throw new CategoryException("No Category Found");
		}
		
		return list;
		
	}

	@Override
	public Category getCategoryById(Long id) throws CategoryException {
		

		Category category = cRepo.findById(id)
				                 .orElseThrow(() -> new CategoryException("No category Found"));
		
		return category;
		
	}

}
