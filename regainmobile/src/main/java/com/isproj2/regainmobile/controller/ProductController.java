package com.isproj2.regainmobile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.services.ProductService;

@RestController
public class ProductController {
    
private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to add a new product
    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product addedProduct = productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    // Endpoint to fetch a product by its ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // Endpoint to update an existing product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(productId, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // Endpoint to delete a product by its ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // // Endpoint to fetch all products (optional)
    // @GetMapping("/all")
    // public ResponseEntity<List<Product>> getAllProducts() {
    //     List<Product> products = productService.getAllProducts();
    //     return ResponseEntity.ok(products);
    // }
}
