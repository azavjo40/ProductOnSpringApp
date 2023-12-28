package com.example.app.product;

import com.example.app.product.dto.ProductCreateDto;
import com.example.app.product.dto.ProductUpdateDto;
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

	public ResponseEntity<Product> getProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Product not found"));
		return ResponseEntity.ok(product);
	}

	public ResponseEntity<String> createProduct(ProductCreateDto product) {

		productRepository.save(product.getProduct());
		return ResponseEntity.ok("Product created");

	}

	public ResponseEntity<String> updateProduct(ProductUpdateDto newProduct, Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Product not found"));

		if (newProduct.getTitle() != null) {
			product.setTitle(newProduct.getTitle());
		}

		if (newProduct.getPrice() != null) {
			product.setPrice(newProduct.getPrice());
		}

		if (newProduct.getDescription() != null) {
			product.setDescription(newProduct.getDescription());
		}

		if (newProduct.getCategory() != null) {
			product.setCategory(newProduct.getCategory());
		}

		if (newProduct.getImage() != null) {
			product.setImage(newProduct.getImage());
		}

		if (newProduct.getCount() != null) {
			product.setCount(newProduct.getCount());
		}

		if (newProduct.getRate() != null) {
			product.setRate(newProduct.getRate());
		}

		productRepository.save(product);
		return ResponseEntity.ok("Product updated");
	}
}
