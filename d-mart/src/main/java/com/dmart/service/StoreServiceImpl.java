package com.dmart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.ProductException;
import com.dmart.exception.StoreException;
import com.dmart.model.MainStorage;
import com.dmart.model.Product;
import com.dmart.model.Store;
import com.dmart.repository.MainStoreRepo;
import com.dmart.repository.ProductRepo;
import com.dmart.repository.StoreRepo;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepo sRepo;

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private MainStorageService mService;

	@Autowired
	private MainStoreRepo mRepo;

	@Override
	public Store addStore(Store store) throws StoreException {

		if (store != null) {
			store = sRepo.save(store);
		}

		else
			throw new StoreException("store can't be null");

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

		Store newStore = sRepo.findById(store.getStoreId()).orElseThrow(() -> new StoreException("No Store Found"));

		newStore = sRepo.save(store);

		return newStore;
	}

	@Override
	public List<Store> getAllStores() throws StoreException {

		List<Store> stores = sRepo.findAll();

		if (stores.size() == 0)
			throw new StoreException("No store found");

		return stores;

	}

	@Override
	public Store getStoreById(Long id) throws StoreException {

		Store store = sRepo.findById(id).orElseThrow(() -> new StoreException("No Store Found"));

		return store;

	}

	@Override
	public List<Store> getAllStoresByProductId(Long pId) throws StoreException, ProductException {

		List<Store> storeList = sRepo.findAll();

		List<Store> newList = new ArrayList<>();

		Product product = pRepo.findById(pId).orElseThrow(() -> new ProductException("No Product found"));

		for (Store s : storeList) {

			HashMap<Product, Long> map = s.getProductQuantity();

//			Set<Product> set = map.keySet();
//			
//			for(Product p:set) {
//				if(p.getProductId()==pId) {
//					
//				}
//			}

			if (map.containsKey(product)) {
				newList.add(s);
			}

		}

		if (newList.size() == 0)
			throw new StoreException("No Store Found");

		return newList;

	}

	@Override
	public List<Product> getAllproductsByStoreId(Long sId) throws StoreException, ProductException {

		Store store = sRepo.findById(sId).orElseThrow(() -> new StoreException("No Store found"));

		HashMap<Product, Long> map = store.getProductQuantity();

		Set<Product> set = map.keySet();

		if (set.size() == 0)
			throw new ProductException("No Product found in the Store..");

		List<Product> list = new ArrayList<>(set);

		return list;

	}

	@Override
	public String addProductToStore(Long sid, Long pId) throws StoreException, ProductException {

		Store store = sRepo.findById(sid).orElseThrow(() -> new StoreException("No Store Found"));

		MainStorage mStore = (MainStorage) mRepo.findAll();

		HashMap<Product, Long> map = mStore.getProductQuantity();

		Set<Product> set = map.keySet();

		for (Product p : set) {
			if (p.getProductId() == pId) {

				HashMap<Product, Long> sMap = store.getProductQuantity();

				if (sMap.containsKey(p)) {
					return "product already available in the store";
				} else {
					sMap.put(p, (long) 0);

					store.setProductQuantity(sMap);

					return "product added to the store successfully";
				}

			}
		}

		throw new ProductException("No product found in the main store, Add it to the main store first");

	}

	@Override
	public String deleteProductToStore(Long sid, Long pId) throws StoreException, ProductException {
		Store store = sRepo.findById(sid).orElseThrow(() -> new StoreException("No Store Found"));

		HashMap<Product,Long> map = store.getProductQuantity();
		
		Set<Product> set = map.keySet();
		
		for(Product s:set) {
			if(s.getProductId()==pId) {
				map.remove(s);
				
				store.setProductQuantity(map);
				
				return "Product removed from the store successfully";
			}
		}
		
		return "Product not found in the store";
	}

	@Override
	public String increaseProductToStore(Long sId, Long pId, Long quantity) throws StoreException, ProductException {
		
		Store store = sRepo.findById(sId).orElseThrow(() -> new StoreException("No Store Found"));
		
		String result = mService.decreaseQuantity(pId, quantity);
		
		if(result.equals("Quantity decreased of the product successfully")) {
			
			HashMap<Product, Long> map = store.getProductQuantity();

			Product product = pRepo.findById(pId)
					.orElseThrow(() -> new ProductException("No product found"));
			
			if(map.containsKey(product)) {
				
				map.put(product, map.get(product)+quantity);
				
				store.setProductQuantity(map);
				
				return "Quantity increased of the product successfully";
			}
			
			else return "Product not found in the store,add product first";
			
			
		}
		else return "result";
	}

	@Override
	public String decreaseProductToStore(Long sId, Long pId, Long quantity) throws StoreException, ProductException {
		
		Store store = sRepo.findById(sId).orElseThrow(() -> new StoreException("No Store Found"));
		
		HashMap<Product, Long> map = store.getProductQuantity();

		Product product = pRepo.findById(pId)
				.orElseThrow(() -> new ProductException("No product found"));
		
		if(map.containsKey(product)) {
			
			if(map.get(product)>=quantity) {
				
			map.put(product, map.get(product)-quantity);
			
			store.setProductQuantity(map);
			
			String result = mService.increaseQuantity(pId, quantity);
			
			if(result.equals("Quantity increased of the product successfully")) {
				return "Quantity decreased of the product successfully";
			}
			
			return result;
			
			}
			else {
				return "Store has already less quantity";
			}
		}
		
		else return "Product not found in the store";
		
	}

}
