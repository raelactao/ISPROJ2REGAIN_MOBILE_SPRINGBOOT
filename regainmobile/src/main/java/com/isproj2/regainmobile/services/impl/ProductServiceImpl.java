package com.isproj2.regainmobile.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ProductService;
import com.isproj2.regainmobile.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Product addProduct(ProductDTO productDTO) {
        User seller = userService.getUserById(productDTO.getSellerID());
        
        Product product = new Product();
        product.setSeller(seller);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());
        product.setLocation(productDTO.getLocation());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        // product.setImage(productDTO.getImage());
        product.setCanDeliver(productDTO.getCanDeliver());

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElseThrow(() -> new IllegalArgumentException("Product Not Found"));
    }

    @Override
    @Transactional
    public Product updateProduct(Integer productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User seller = userService.getUserById(productDTO.getSellerID());
     
        // Update fields based on DTO
        product.setSeller(seller);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());
        product.setLocation(productDTO.getLocation());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        // product.setImage(productDTO.getImage());
        product.setCanDeliver(productDTO.getCanDeliver());

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    // @Override
    // public List<Product> getAllProducts() {
    //     return productRepository.findAll();
    // }
}
