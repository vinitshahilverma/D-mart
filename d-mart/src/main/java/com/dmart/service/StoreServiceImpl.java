package com.dmart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.ProductException;
import com.dmart.exception.StoreException;
import com.dmart.model.Product;
import com.dmart.model.Store;
import com.dmart.repository.StoreRepo;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreRepo sRepo;

	@Override
	public Store addStore(Store store) throws StoreException {
		
		if(store!=null) {
			store = sRepo.save(store);
		}
		
		else throw new StoreException("store can't be null");
		
		return store;
	}

	@Override
	public Store deleteStore(Long id) throws StoreException {
		
		Store store = sRepo.findById(id).orElseThrow(() -> new StoreException("No Store found"));
		
		sRepo.delete(store);
		
		return store;
	}

	@Override
	public Store updateStore(Store store) throws StoreException {
		
		Store newStore = sRepo.findById(store.getStoreId())
				.orElseThrow(() -> new StoreException("No Store Found"));

		newStore = sRepo.save(store);

		return newStore;
	}

	@Override
	public List<Store> getAllStores() throws StoreException {
		
		List<Store> stores =  sRepo.findAll();
		
		if(stores.size()==0) throw new StoreException("No store found");
		
		return stores;
		
	}

	@Override
	public Store getStoreById(Long id) throws StoreException {
		
		Store store = sRepo.findById(id).orElseThrow(() -> new StoreException("No Store Found"));
		
		return store;
		
		
	}

	@Override
	public List<Store> getAllStoresByProductId(Long pId) throws StoreException {
		
		List<Store> storeList = sRepo.findAll();
		
		List<Store> newList = new ArrayList<>();
		
		if(storeList.size()==0) throw new StoreException("No store found");
		
		for(Store s:storeList) {
			
			List<Product> products = s.getProductList();
			
			for(Product p:products) {
				if(p.getProductId()==pId) {
					newList.add(s);
					break;
				}
			}
			
		}
		
		if(newList.size()==0)throw new StoreException("No Store contains this product");
		
		return newList;
		
	}

	@Override
	public List<Product> getAllproductsByStoreId(Long sId) throws StoreException {
		
		Store store  = sRepo.findById(sId).orElseThrow(() -> new StoreException("No Store found"));
		
		List<Product> list = store.getProductList();
		
		return list;
		
	}

	@Override
	public String addProductToStore(Long sid, Long pId) throws StoreException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProductToStore(Long sid, Long pId) throws StoreException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String increaseProductToStore(Long sId, Long pId, Long quantity) throws StoreException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decreaseProductToStore(Long sId, Long pId, Long quantity) throws StoreException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
