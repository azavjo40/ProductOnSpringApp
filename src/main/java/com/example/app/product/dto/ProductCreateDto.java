package com.example.app.product.dto;

import com.example.app.product.entities.Product;
import jakarta.validation.constraints.*;

public class ProductCreateDto {
	@NotBlank(message = "Title cannot be blank")
	private String title;

	@NotNull(message = "Price cannot be null")
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private Float price;

	@NotBlank(message = "Description cannot be blank")
	@Size(max = 1000, message = "Description cannot be more than 1000 characters")
	private String description;

	@NotBlank(message = "Category cannot be blank")
	private String category;

	@NotBlank(message = "Image URL cannot be blank")
	@Pattern(regexp = "^(http|https)://.*", message = "Image URL must be a valid URL")
	private String image;

	@NotNull(message = "Count cannot be null")
	@Min(value = 0, message = "Count must be a non-negative number")
	private Integer count;

	@NotNull(message = "Rate cannot be null")
	@Min(value = 0, message = "Rate must be greater than or equal to 0")
	private Float rate;

	public ProductCreateDto(String title, Float price, String description, String category, String image, Integer count, Float rate) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.count = count;
		this.rate = rate;
	}

	public Product getProduct() {
		return new Product(this.title, this.price, this.description, this.category, this.image, this.count, this.rate);
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
