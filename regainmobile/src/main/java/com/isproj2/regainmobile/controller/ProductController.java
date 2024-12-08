package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ImageValidateService;
import com.isproj2.regainmobile.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageValidateService imageValidateService;

   @PostMapping("/add")
public ResponseEntity<?> createProduct(
        @RequestParam("productName") String productName,
        @RequestParam("description") String description,
        @RequestParam("weight") String weight,
        @RequestParam("location") Integer location,
        @RequestParam("categoryID") Integer categoryID,
        @RequestParam("price") String price,
        @RequestParam("canDeliver") Boolean canDeliver,
        @RequestParam(value = "image", required = false) MultipartFile image,
        @RequestParam("sellerID") Integer sellerID) {
    try {
        // Logging the received data
        log.info("Received productName: {}", productName);
        log.info("Received description: {}", description);
        log.info("Received weight: {}", weight);
        log.info("Received location: {}", location);
        log.info("Received categoryID: {}", categoryID);
        log.info("Received price: {}", price);
        log.info("Received canDeliver: {}", canDeliver);
        log.info("Received sellerID: {}", sellerID);
        if (image != null && !image.isEmpty()) {
            log.info("Received image: {}", image.getOriginalFilename());
        }

        // Create ProductDTO from form data
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setDescription(description);
        productDTO.setWeight(weight);
        productDTO.setLocation(location);
        productDTO.setCategoryID(categoryID);
        productDTO.setPrice(price);
        productDTO.setCanDeliver(canDeliver);
        productDTO.setSellerID(sellerID);

        // Handle image
        if (image != null && !image.isEmpty()) {
            imageValidateService.validateImageFile(image);
            productDTO.setImage(image.getBytes());
        }

        // Save the product
        ProductDTO createdProduct = productService.createProduct(productDTO);
        log.info("Product created successfully: {}", createdProduct);

        return ResponseEntity.ok(createdProduct);
    } catch (Exception e) {
        // Log the error for debugging
        log.error("Error occurred while creating product: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while creating product: " + e.getMessage());
    }
}


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable("id") Integer productId,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("weight") String weight,
            @RequestParam("location") Integer location,
            @RequestParam("categoryID") Integer categoryID,
            @RequestParam("price") String price,
            @RequestParam("canDeliver") Boolean canDeliver,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("sellerID") Integer sellerID) {
        try {
            // Fetch the existing product and map updated fields
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductID(productId);
            productDTO.setProductName(productName);
            productDTO.setDescription(description);
            productDTO.setWeight(weight);
            productDTO.setLocation(location);
            productDTO.setCategoryID(categoryID);
            productDTO.setPrice(price);
            productDTO.setCanDeliver(canDeliver);
            productDTO.setSellerID(sellerID);

            // Handle image update
            if (image != null && !image.isEmpty()) {
                imageValidateService.validateImageFile(image);
                productDTO.setImage(image.getBytes());
            }

            // Update the product
            ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);

            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

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

    @GetMapping("/userviewlist/{id}")
    public ResponseEntity<List<ViewProductDTO>> getViewProductsByUser(@PathVariable("id") Integer userId) {
        List<ViewProductDTO> products = productService.getViewProductsByUser(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/viewlist/{id}")
    public ResponseEntity<List<ViewProductDTO>> getAllProductsByUserFavorites(@PathVariable("id") Integer userId) {
        List<ViewProductDTO> productsWithFavorites = productService.getViewProducts(userId);
        return ResponseEntity.ok(productsWithFavorites);
    }

    @GetMapping("/view/filter")
    public List<ViewProductDTO> getViewProductsByCategory(@RequestParam String category, @RequestParam Integer userId) {
    return productService.getViewProductsByCategory(category, userId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ViewProductDTO>> searchProducts(
            @RequestParam String query, @RequestParam Integer userId) {
        List<ViewProductDTO> searchResults = productService.searchViewProducts(query, userId);
        return ResponseEntity.ok(searchResults);
    }
}