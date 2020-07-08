package com.productscrud.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details for Product Model")
@Entity
@Table(name="products")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	
	@ApiModelProperty(notes="Name should be minimum 2 " 
					+ " and maximum 50 characters.")
	@Size(min=2,max=50,
			message="Name should be minimum 2 "
					+ "and maximum 50 characters.")
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private int price;
	
	
	@ApiModelProperty(notes="Description should be maximum 2000 characters.")
	@Size(max=2000,message="Description should be maximum 2000 characters.")
	@Column(name="description")
	private String description;
	
	
	public Product() {
		super();
	}


	public Product(long id, String name, int price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}


	


		
}
