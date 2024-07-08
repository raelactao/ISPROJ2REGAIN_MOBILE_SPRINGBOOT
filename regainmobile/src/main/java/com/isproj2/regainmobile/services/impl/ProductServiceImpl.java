package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        User seller = userRepository.findById(productDTO.getSellerID())
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id " + productDTO.getSellerID()));

        Product product = new Product(productDTO, seller);
        productRepository.save(product);
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        User seller = userRepository.findById(productDTO.getSellerID())
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id " + productDTO.getSellerID()));

        product.setSeller(seller);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());
        product.setLocation(productDTO.getLocation());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setCanDeliver(productDTO.getCanDeliver());

        productRepository.save(product);
        return productDTO;
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
        return new ProductDTO(product.getProductID(), product.getSeller().getId(), product.getProductName(), product.getDescription(), product.getWeight(), product.getLocation(), product.getCategory(), product.getPrice(), product.getCanDeliver());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getProductID(), product.getSeller().getId(), product.getProductName(), product.getDescription(), product.getWeight(), product.getLocation(), product.getCategory(), product.getPrice(), product.getCanDeliver()))
                .collect(Collectors.toList());
    }
}