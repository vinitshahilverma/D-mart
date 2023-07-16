package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmart.model.MainStorage;

@Repository
public interface MainStoreRepo extends JpaRepository<MainStorage, Integer>{

}
