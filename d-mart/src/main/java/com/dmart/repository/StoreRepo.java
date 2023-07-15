package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmart.model.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long>{

}
