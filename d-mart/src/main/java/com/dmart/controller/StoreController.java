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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.ProductException;
import com.dmart.exception.StoreException;
import com.dmart.model.Product;
import com.dmart.model.Store;
import com.dmart.service.StoreService;

@RestController
@RequestMapping("/dmart")
public class StoreController {
	
	@Autowired
	private StoreService sService;
	
	@PostMapping("/addStore")
	public ResponseEntity<Store> addStoreHandler(@RequestBody Store store) throws StoreException{
		
		Store newStore = sService.addStore(store);
		
		return new ResponseEntity<Store>(newStore,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateStore")
	public ResponseEntity<Store> updateStoreHandler(@RequestBody Store store) throws StoreException{
		
		Store newStore = sService.updateStore(store);
		
		return new ResponseEntity<Store>(newStore,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/deleteStore/{id}")
	public ResponseEntity<Store> deleteStoreHandler(@PathVariable Long id) throws StoreException{
		
		Store newStore = sService.deleteStore(id);
		
		return new ResponseEntity<Store>(newStore,HttpStatus.OK);
	}
	
	
	@GetMapping("/getStoreById/{id}")
	public ResponseEntity<Store> getStoreByIdHandler(@PathVariable Long id) throws StoreException{
		
		Store newStore = sService.getStoreById(id);
		
		return new ResponseEntity<Store>(newStore,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllStores")
	public ResponseEntity<List<Store>> getAllStoresHandler() throws StoreException{
		
		List<Store> newStore = sService.getAllStores();
		
		return new ResponseEntity<List<Store>>(newStore,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProductsByStoreId/{id}")
	public ResponseEntity<List<Product>> getAllProductsByStoreIdHandler(@PathVariable Long id) throws StoreException, ProductException{
		
		List<Product> productList = sService.getAllproductsByStoreId(id);
		
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	@GetMapping("/getAllStoresByProductId/{id}")
	public ResponseEntity<List<Store>> getAllStoresByProductIdHandler(@PathVariable Long id) throws StoreException, ProductException{
		
		List<Store> productList = sService.getAllStoresByProductId(id);
		
		return new ResponseEntity<List<Store>>(productList,HttpStatus.OK);
	}
	
	
	@PostMapping("/addProductToStore/{sId}/{pId}")
	public ResponseEntity<String> addProductToStoreHandler(@PathVariable Long sId,@PathVariable Long pId) throws StoreException, ProductException{
		
		String result = sService.addProductToStore(sId, pId);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/deleteProductFromStore/{sId}/{pId}")
	public ResponseEntity<String> addProductToFromHandler(@PathVariable Long sId,@PathVariable Long pId) throws StoreException, ProductException{
		
		String result = sService.deleteProductToStore(sId, pId);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/increaseProductToStore/{sId}/{pId}")
	public ResponseEntity<String> increaseProductToStoreHandler(@PathVariable Long sId,@PathVariable Long pId,@RequestParam Long quantity) throws StoreException, ProductException{
		
		String result = sService.increaseProductToStore(sId, pId, quantity);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/decreaseProductToStore/{sId}/{pId}")
	public ResponseEntity<String> decreaseProductToStoreHandler(@PathVariable Long sId,@PathVariable Long pId,@RequestParam Long quantity) throws StoreException, ProductException{
		
		String result = sService.decreaseProductToStore(sId, pId, quantity);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	

}
