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
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.ProductException;
import com.dmart.model.Product;
import com.dmart.service.productService;

@RestController
@RequestMapping("/dmart")
public class ProductController {
	
	@Autowired
	private productService pService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProductHandler(@RequestBody Product product) throws ProductException{
		
		Product newProduct = pService.addProduct(product);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product) throws ProductException{
		
		Product newProduct = pService.updateProduct(product);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Product> deleteProductHandler(@PathVariable Long id) throws ProductException{
		
		Product newProduct = pService.deleteProduct(id);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.OK);
	}
	
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductByIdHandler(@PathVariable Long id) throws ProductException{
		
		Product newProduct = pService.getProductById(id);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProductHandler() throws ProductException{
		
		List<Product> newProduct = pService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(newProduct,HttpStatus.OK);
	}

}
