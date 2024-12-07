package com.isproj2.regainmobile.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseModel<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseModel<ProductDTO>(HttpStatus.OK.value(), "Product up for verification", createdProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Integer productId,
            @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel<Void> deleteProduct(@PathVariable("id") Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseModel<>(HttpStatus.OK.value(), "Product deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        return ResponseEntity.ok(productDTO);
    }

    // @GetMapping("/list")
    // public ResponseEntity<List<ProductDTO>> getAllProducts() {
    // List<ProductDTO> products = productService.getAllProducts();
    // return ResponseEntity.ok(products);
    // }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByUser(@PathVariable("id") Integer userId) {
        List<ProductDTO> products = productService.getProductsByUser(userId);
        return ResponseEntity.ok(products);
    }
    // @GetMapping("/list/{id}")
    // public ResponseEntity<List<ViewProductDTO>>
    // getAllProductsByUser(@PathVariable("id") Integer userId) {
    // List<ViewProductDTO> products = productService.getViewProductsByUser(userId);
    // return ResponseEntity.ok(products);
    // }

    // to get viewproducts by id user
    @GetMapping("/userviewlist/{id}")
    public ResponseEntity<List<ViewProductDTO>> getViewProductsByUser(@PathVariable("id") Integer userId) {
        List<ViewProductDTO> products = productService.getViewProductsByUser(userId);
        return ResponseEntity.ok(products);
    }

    // get ALL viewproducts with favorites of id user
    // (ALL products and displays whether favorited)
    @GetMapping("/viewlist/{id}")
    public ResponseEntity<List<ViewProductDTO>> getAllProductsByUserFavorites(@PathVariable("id") Integer userId) {
        List<ViewProductDTO> productsWithFavorites = productService.getViewProducts(userId);
        return ResponseEntity.ok(productsWithFavorites);
    }

}