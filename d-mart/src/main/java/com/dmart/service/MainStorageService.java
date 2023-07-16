package com.dmart.service;

import com.dmart.exception.ProductException;
import com.dmart.model.MainStorage;

public interface MainStorageService{
	
	public String addMainStorage(MainStorage mStore);
	
	public String deleteMainStorage();
	
	public String addProductToMainStorage(Long pId)throws ProductException;
	
	public String deleteProductToMainStorage(Long pId)throws ProductException;
	
	public String increaseQuantity(Long pId,Long quantity)throws ProductException;
	
	public String decreaseQuantity(Long pId,Long quantity)throws ProductException;

}
