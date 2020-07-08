package com.productscrud.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productscrud.products.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long >{

}
	