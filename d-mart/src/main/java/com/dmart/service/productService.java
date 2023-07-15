package com.dmart.service;

import java.util.List;

import com.dmart.exception.ProductException;
import com.dmart.model.Product;

public interface productService {
	
	public Product addProduct(Product product)throws ProductException;
	
	public Product deleteProduct(Long id)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public List<Product> getAllProducts()throws ProductException;
	
	public Product getProductById(Long id)throws ProductException;
	
//	public Product increaseQuantity(Long id,Long quantity)throws ProductException;
//	
//	public Product decreaseQuantity(Long id,Long quantity)throws ProductException;

}
