package com.dmart.service;

import java.util.List;

import com.dmart.exception.ProductException;
import com.dmart.exception.StoreException;
import com.dmart.model.Product;
import com.dmart.model.Store;

public interface StoreService {

	public Store addStore(Store store) throws StoreException;

	public Store deleteStore(Long id) throws StoreException;

	public Store updateStore(Store store) throws StoreException;

	public List<Store> getAllStores() throws StoreException;

	public Store getStoreById(Long id) throws StoreException;

	public List<Product> getAllproductsByStoreId(Long sId) throws StoreException;

	public List<Store> getAllStoresByProductId(Long pId) throws StoreException;

	public String addProductToStore(Long sid,Long pId) throws StoreException, ProductException;
	
	public String deleteProductToStore(Long sid,Long pId) throws StoreException, ProductException;
	
	public String increaseProductToStore(Long sId,Long pId,Long quantity)throws StoreException,ProductException;
	
	public String decreaseProductToStore(Long sId,Long pId,Long quantity)throws StoreException,ProductException;
}
