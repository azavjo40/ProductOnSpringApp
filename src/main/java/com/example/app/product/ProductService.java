package com.example.app.product;

import com.example.app.common.dto.ResponseDto;
import com.example.app.product.dto.ProductCreateDto;
import com.example.app.product.dto.ProductUpdateDto;
import com.example.app.product.entities.Product;
import com.example.app.product.repositories.ProductRepository;
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

	public ResponseEntity<ResponseDto<List<Product>>> getProducts() {
		List<Product> products = productRepository.findAll();
		ResponseDto<List<Product>> productsResponse = new ResponseDto<>(products, "Success");
		return ResponseEntity.ok(productsResponse);
	}

	public ResponseEntity<ResponseDto<Product>> getProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Product not found"));
		ResponseDto<Product> productResponse = new ResponseDto<>(product, "Success");
		return ResponseEntity.ok(productResponse);
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
