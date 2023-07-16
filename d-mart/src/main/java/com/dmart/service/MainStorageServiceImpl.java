package com.dmart.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.ProductException;
import com.dmart.model.MainStorage;
import com.dmart.model.Product;
import com.dmart.repository.MainStoreRepo;
import com.dmart.repository.ProductRepo;

@Service
public class MainStorageServiceImpl implements MainStorageService {

	@Autowired
	private MainStoreRepo mRepo;

	@Autowired
	private ProductRepo pRepo;

    @Override
	public String addProductToMainStorage(Long pId) throws ProductException {

		MainStorage mStore = (MainStorage) mRepo.findAll();

		HashMap<Product, Long> map = mStore.getProductQuantity();

		Product product = pRepo.findById(pId)
				.orElseThrow(() -> new ProductException("No product found add product first"));
		
		if(map.containsKey(product)) {
			return "Product already in the Store";
		}
		else {
			map.put(product, (long) 0);
			
			mStore.setProductQuantity(map);
			
			return "Product added successfully";
		}

	}

	@Override
	public String deleteProductToMainStorage(Long pId) throws ProductException {
		
		MainStorage mStore = (MainStorage) mRepo.findAll();

		HashMap<Product, Long> map = mStore.getProductQuantity();

		Product product = pRepo.findById(pId)
				.orElseThrow(() -> new ProductException("No product found"));
		
		if(map.containsKey(product)) {
			map.remove(product);
			
			mStore.setProductQuantity(map);
			
			return "Product deleted from the store successfully";
		}
		
		else return "Product not found in the store";
		
	}

	@Override
	public String increaseQuantity(Long pId, Long quantity) throws ProductException {
		
		MainStorage mStore = (MainStorage) mRepo.findAll();

		HashMap<Product, Long> map = mStore.getProductQuantity();

		Product product = pRepo.findById(pId)
				.orElseThrow(() -> new ProductException("No product found"));
		
		if(map.containsKey(product)) {
			
			map.put(product, map.get(product)+quantity);
			
			mStore.setProductQuantity(map);
			
			return "Quantity increased of the product successfully";
		}
		
		else return "Product not found in the store";
		
		
	}

	@Override
	public String decreaseQuantity(Long pId, Long quantity) throws ProductException {
		
		MainStorage mStore = (MainStorage) mRepo.findAll();

		HashMap<Product, Long> map = mStore.getProductQuantity();

		Product product = pRepo.findById(pId)
				.orElseThrow(() -> new ProductException("No product found"));
		
		if(map.containsKey(product)) {
			
			if(map.get(product)>=quantity) {
				
			map.put(product, map.get(product)-quantity);
			
			mStore.setProductQuantity(map);
			
			return "Quantity decreased of the product successfully";
			
			}
			else {
				return "Store has already less quantity";
			}
		}
		
		else return "Product not found in the store";
		
	}

	@Override
	public String addMainStorage(MainStorage mStore) {
		
		mStore = mRepo.save(mStore);
		
		return "Main Storage added successfully";
		
	}

	@Override
	public String deleteMainStorage() {
		
		MainStorage mStore = (MainStorage) mRepo.findAll();
		
		mRepo.delete(mStore);
		
		return "Main Storage deleted successfully";
		
	}

}
