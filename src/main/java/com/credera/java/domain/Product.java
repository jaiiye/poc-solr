package com.credera.java.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "id")
	private Long id;
	@Formula("CAST(id as varchar(255))")
	private String idString;
		
	@Column(name = "sku")
	private String sku;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "EMPLOYEE_ID")
//	@NotFound(action=NotFoundAction.IGNORE)
	@Column(name = "manufacturer")
	private String manufacturer;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private double price;

	public Product() {
		// TOTO stub...
	}
	
	public Product(Long id, String sku, String manufacturer,
			String name, String description, double price) {
		super();
		this.id = id;
		this.sku = sku;
		this.manufacturer = manufacturer;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", idString=");
		builder.append(idString);
		builder.append(", sku=");
		builder.append(sku);
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
