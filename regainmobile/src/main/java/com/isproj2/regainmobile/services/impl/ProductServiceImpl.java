package com.isproj2.regainmobile.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Category;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.CategoryRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public ProductDTO createProduct(ProductDTO productDTO) {
                User seller = userRepository.findById(productDTO.getSellerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Seller not found with id " + productDTO.getSellerID()));

                Category categ = categoryRepository.findById(productDTO.getCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + productDTO.getCategoryID()));

                Product product = new Product(productDTO, seller, categ);
                productRepository.save(product);
                return productDTO;
        }

        @Override
        public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
                Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + productId));

                User seller = userRepository.findById(productDTO.getSellerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + productDTO.getSellerID()));

                Category categ = categoryRepository.findById(productDTO.getCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + productDTO.getCategoryID()));

                product.setSeller(seller);
                product.setCategory(categ);
                product.setProductName(productDTO.getProductName());
                product.setDescription(productDTO.getDescription());
                product.setWeight(productDTO.getWeight());
                product.setLocation(productDTO.getLocation());
                // product.setCategory(categoryRepository.findByCategoryID(productDTO.getCategoryID()));
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
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + productId));
                return new ProductDTO(product.getProductID(), product.getSeller().getUserID(), product.getProductName(),
                                product.getDescription(), product.getWeight(), product.getLocation(),
                                product.getCategory().getCategoryID(),
                                product.getPrice(), product.getCanDeliver());
        }

        // @Override
        // public List<ProductDTO> getAllProducts() {
        // return productRepository.findAll();
        // }

        @Override
        public List<ProductDTO> getAllProducts() {
                return productRepository.findAll().stream()
                                .map(product -> new ProductDTO(product.getProductID(),
                                                product.getSeller().getUserID(),
                                                product.getProductName(), product.getDescription(), product.getWeight(),
                                                product.getLocation(),
                                                product.getCategory().getCategoryID(), product.getPrice(),
                                                product.getCanDeliver()))
                                .collect(Collectors.toList());
        }

        @Override
        public List<ProductDTO> getProductsByUser(Integer userId) {
                List<ProductDTO> sellerProducts = new ArrayList<ProductDTO>();

                User seller = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + userId));

                List<Product> products = productRepository.findBySeller(seller);
                for (Product product : products) {
                        ProductDTO newProd = new ProductDTO(product);
                        sellerProducts.add(newProd);
                }
                // List<ProductDTO> sellerProducts =

                // TODO Auto-generated method stub
                return sellerProducts;
        }

}