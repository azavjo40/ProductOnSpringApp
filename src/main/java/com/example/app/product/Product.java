package com.example.app.product;

import jakarta.persistence.*;

@Entity
@Table(name = "app_product")
public class Product {
	@Id
	@SequenceGenerator(
			name = "product_sequence",
			sequenceName = "product_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "product_sequence"
	)
	private Long id;
	private String title;
	private Float price;
	private String description;
	private String category;
	private String image;
	private Integer count;
	private Float rate;


	public Product() {

	}

	public Product(String title, Float price, String description, String category, String image, Integer count, Float rate) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rate = rate;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", category='" + category + '\'' +
				", image='" + image + '\'' +
				", count=" + count +
				", rate=" + rate +
				'}';
	}
}
