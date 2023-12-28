package com.example.product.product;

import com.example.product.product.dto.ProductCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}

	public ResponseEntity<String> createProduct(ProductCreateDto product) {

		productRepository.save(product.getProduct());
		return ResponseEntity.ok("Product created");

	}

}
