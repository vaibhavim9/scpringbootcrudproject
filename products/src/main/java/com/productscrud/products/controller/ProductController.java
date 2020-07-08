package com.productscrud.products.controller;

import static java.util.Comparator.comparing;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productscrud.products.exception.ResourceNotFoundException;
import com.productscrud.products.model.Product;
import com.productscrud.products.repository.ProductsRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductsRepository productsRepository;
	/*
	 * To retrive list of all prodcuts
	 */
	@GetMapping("/products")
    public List<Product> getAllProducts() {
		List<Product> allProducts= productsRepository.findAll();
		allProducts.sort(comparing(Product::getId));
        return allProducts;
    }
	
	/*
	 * To retrive prodcut by its id
	 */
	@GetMapping("/products/{id}")
    public ResponseEntity<Product> getProducById(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException {
        Optional<Product> optProduct = productsRepository.findById(id);
        if (!optProduct.isPresent())
         throw new ResourceNotFoundException("Product not found for this id :: " + id);
         
        
        return ResponseEntity.ok().body(optProduct.get());
    }
	/*
	 * To  add new product  
	 */
	@PostMapping("/products")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Product product) {
        Product savedProduct= productsRepository.save(product);
        
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedProduct.getId()).toUri();
        
        return ResponseEntity.created(location).build();
    }

	/*
	 * To update any product by id
	 */
	@PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable(value = "id") Long id,
    		@Valid @RequestBody Product reqProduct) throws ResourceNotFoundException {
		
		 Optional<Product> optProduct = productsRepository.findById(id);
		
		 if(!optProduct.isPresent())
			 throw new ResourceNotFoundException("Product not found for this id :: " + id);
			
		Product product = optProduct.get();
		if(reqProduct.getName()!=null) {
			product.setName(reqProduct.getName());
		}
		if(!(reqProduct.getPrice()==0)) {
			product.setPrice(reqProduct.getPrice());
	
		}
		if(reqProduct.getDescription()!=null) {
		
			product.setDescription(reqProduct.getDescription());
		}
		
        final Product updatedProduct = productsRepository.save(product);
        
        
        return ResponseEntity.ok(updatedProduct);
    }
	
	 @DeleteMapping("/products/{id}")
	 public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
	         throws ResourceNotFoundException {
		 
		 Optional<Product> optProduct = productsRepository.findById(id);
			
		 if(!optProduct.isPresent())
			 throw new ResourceNotFoundException("Product not found for this id :: " + id);
			
		
	        productsRepository.delete(optProduct.get());
	        
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        
	        return response;
	    }
}
