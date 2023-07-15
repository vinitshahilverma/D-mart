package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmart.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
