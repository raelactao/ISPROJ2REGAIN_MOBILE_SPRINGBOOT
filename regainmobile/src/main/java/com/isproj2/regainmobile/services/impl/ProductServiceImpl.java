package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.Category;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.repo.CategoryRepository;
import com.isproj2.regainmobile.repo.FavoriteRepository;
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

        @Autowired
        private AddressRepository addressRepository;

        @Autowired
        private FavoriteRepository favoriteRepository;

        @Override
        public ProductDTO createProduct(ProductDTO productDTO) {
                User seller = userRepository.findById(productDTO.getSellerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + productDTO.getSellerID()));

                Category categ = categoryRepository.findById(productDTO.getCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + productDTO.getCategoryID()));

                Address loc = addressRepository.findById(productDTO.getLocation())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + productDTO.getCategoryID()));

                Product product = new Product(productDTO, seller, loc, categ);
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

                Address loc = addressRepository.findById(productDTO.getLocation())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Category not found with id " + productDTO.getCategoryID()));

                product.setSeller(seller);
                product.setCategory(categ);
                product.setProductName(productDTO.getProductName());
                product.setDescription(productDTO.getDescription());
                product.setWeight(Double.parseDouble(productDTO.getWeight()));
                product.setLocation(loc);
                // product.setCategory(categoryRepository.findByCategoryID(productDTO.getCategoryID()));
                product.setPrice(new BigDecimal(productDTO.getPrice()));
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
                                product.getDescription(), product.getWeight().toString(),
                                product.getLocation().getAddressID(),
                                product.getCategory().getCategoryID(),
                                product.getPrice().toString(), product.getCanDeliver());
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
                                                product.getProductName(), product.getDescription(),
                                                product.getWeight().toString(),
                                                product.getLocation().getAddressID(),
                                                product.getCategory().getCategoryID(), product.getPrice().toString(),
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

                return sellerProducts;
        }

        @Override
        public List<ViewProductDTO> getViewProducts(Integer userId) {
                // output a viewmodel list of product that shows whether given userId has
                // favorited the product
                List<ViewProductDTO> viewProducts = new ArrayList<ViewProductDTO>();

                List<Product> products = productRepository.findAll();
                Collections.reverse(products);
                List<Favorite> userFaves = favoriteRepository.findByUser(userRepository.findByUserID(userId));
                // int counter = 0;

                // products.intersection

                for (Product product : products) {
                        boolean favorited = false;
                        // int lastInd = viewProducts.size() - 1;
                        // counter++;

                        // check for user's faved products among all products
                        for (Favorite fave : userFaves) {
                                if (fave.getProduct().equals(product)) {
                                        favorited = true;
                                }
                        }

                        ViewProductDTO viewProd = new ViewProductDTO(product, favorited);

                        // ViewProductDTO viewProd = new ViewProductDTO(
                        // product.getProductID(),
                        // product.getProductName(),
                        // product.getLocation().getCity(),
                        // product.getPrice(),
                        // product.getSeller().getUsername(),
                        // // product.getDescription(),
                        // product.getWeight(),
                        // product.getCategory().getName(),
                        // product.getCanDeliver(),
                        // favorited);
                        viewProducts.add(viewProd);
                        // if (viewProducts.isEmpty()) {
                        // viewProducts.add(viewProd);
                        // } else
                        // viewProducts.add(0, viewProd);

                }
                return viewProducts;

        }

}