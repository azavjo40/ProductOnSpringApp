package com.example.app.product;

import com.example.app.product.dto.ProductCreateDto;
import com.example.app.product.dto.ProductUpdateDto;
import com.example.app.product.entities.Product;
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

	@GetMapping(path = "one/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody @Valid ProductCreateDto product) {
		return productService.createProduct(product);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductUpdateDto product, @PathVariable("id") Long id) {
		return productService.updateProduct(product, id);
	}
}
