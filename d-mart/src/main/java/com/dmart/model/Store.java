package com.dmart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long storeId;
	
	@Embedded
	private Address address;
	
	@ElementCollection
	private HashMap<Product,Long> productQuantity = new HashMap<>();

}
