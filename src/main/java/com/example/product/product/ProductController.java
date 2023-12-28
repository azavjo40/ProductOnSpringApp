package com.example.product.product;

import com.example.product.product.dto.ProductCreateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(path = "all")
	public ResponseEntity<List<Product>> getProducts() {
		return productService.getProducts();
	}

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody @Valid ProductCreateDto product) {
		return productService.createProduct(product);
	}
}
