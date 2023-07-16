package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.ProductException;
import com.dmart.model.MainStorage;
import com.dmart.service.MainStorageService;

@RestController
@RequestMapping("/dmart")
public class MainStorageController {
	
	@Autowired
	private MainStorageService mService;
	
	//API to add Main Storage
	
	@PostMapping("/addMainStorage")
	public ResponseEntity<String> addMainStorageHandler(@RequestBody MainStorage mStorage){
		
		String result = mService.addMainStorage(mStorage);
		
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
		
	}
	
	//API to delete Main Storage
	
	@DeleteMapping("/deleteMainStorage")
	public ResponseEntity<String> deleteMainStorageHandler(){
		
		String result = mService.deleteMainStorage();
		
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
		
	}
	
	//API to add Products to Main Storage
	
	@PutMapping("/addProductToMainStorage/{id}")
	public ResponseEntity<String> addProdcutToMainStorageHandler(@PathVariable Long id) throws ProductException{
		
		String result = mService.addProductToMainStorage(id);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
		
	}
	
	//API to delete Products from Main Storage through their respected product Id;
	
	@PutMapping("/deleteProductToMainStorage/{id}")
	public ResponseEntity<String> deleteProdcutToMainStorageHandler(@PathVariable Long id) throws ProductException{
		
		String result = mService.deleteProductToMainStorage(id);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
		
	}
	
	//API to increase available Product Quantity in the Main Storage
	
	@PutMapping("/increaseQuantityToMainStorage/{pId}")
	public ResponseEntity<String> increaseQuantityToMainStorageHandler(@PathVariable Long pId,@RequestParam Long quantity) throws ProductException{
		
		String result = mService.increaseQuantity(pId, quantity);
		
		return new ResponseEntity<String>(result,HttpStatus.ACCEPTED);
		
	}
	
	//API to decrease available Product Quantity from Main Storage
	
	@PutMapping("/decreaseQuantityToMainStorage/{pId}")
	public ResponseEntity<String> decreaseQuantityToMainStorageHandler(@PathVariable Long pId,@RequestParam Long quantity) throws ProductException{
		
		String result = mService.decreaseQuantity(pId, quantity);
		
		return new ResponseEntity<String>(result,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
	
	

}
