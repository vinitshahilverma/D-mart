package com.dmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.ProductException;
import com.dmart.model.Product;
import com.dmart.repository.ProductRepo;

import jakarta.transaction.Synchronization;
import lombok.Synchronized;

@Service
public class productServiceImpl implements productService {

	@Autowired
	private ProductRepo pRepo;

	@Override
	public Product addProduct(Product product) throws ProductException {

		if (product != null) {
			pRepo.save(product);
		} else
			throw new ProductException("Product can't be empty");

		return product;
	}

	@Override
	public Product deleteProduct(Long id) throws ProductException {

		Product product = pRepo.findById(id).orElseThrow(() -> new ProductException("No product found"));

		pRepo.delete(product);

		return product;

	}

	@Override
	public Product updateProduct(Product product) throws ProductException {

		Product newProduct = pRepo.findById(product.getProductId())
				.orElseThrow(() -> new ProductException("No Product Found"));

		newProduct = pRepo.save(product);

		return newProduct;
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {

		List<Product> productList = pRepo.findAll();

		if (productList.size() == 0) {
			throw new ProductException("No product found");
		}

		return productList;

	}

	@Override
	public Product getProductById(Long id) throws ProductException {

		Product product = pRepo.findById(id).orElseThrow(() -> new ProductException("No product found"));

		return product;
	}

//	@Override
//	public Product increaseQuantity(Long id, Long quantity) throws ProductException {
//
//		Product product = pRepo.findById(id).orElseThrow(() -> new ProductException("No product found"));
//
//		product.setQuantity(product.getQuantity() + quantity);
//
//		product = pRepo.save(product);
//
//		return product;
//
//	}
//
//	@Override
//	public Product decreaseQuantity(Long id, Long quantity) throws ProductException {
//
//		Product product = pRepo.findById(id).orElseThrow(() -> new ProductException("No product found"));
//
//		synchronized (product) {
//
//			if (product.getQuantity() >= quantity) {
//				
//				product.setQuantity(product.getQuantity() - quantity);
//
//				product = pRepo.save(product);
//				
//				return product;
//			}
//			else throw new ProductException("Product quantity can't be dcreased, Enter less quantity.");
//
//		}
//
//	}

}
