package com.isproj2.regainmobile.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ImageValidateService;
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

        @Autowired
        private ImageValidateService validationService;

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
                                                "Address not found with id " + productDTO.getLocation()));

                Product product = new Product(productDTO, seller, loc, categ);
                product.setStatus("Pending");

                if (productDTO.getImage() != null) {
                        product.setImage(productDTO.getImage());
                    }
                    
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
                product.setWeight(new BigDecimal(productDTO.getWeight()));
                product.setLocation(loc);
                // product.setCategory(categoryRepository.findByCategoryID(productDTO.getCategoryID()));
                product.setPrice(new BigDecimal(productDTO.getPrice()));
                product.setCanDeliver(productDTO.getCanDeliver());

                if (productDTO.getImage() != null) {
                        product.setImage(productDTO.getImage());
                }                

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
                                product.getPrice().toString(), product.getCanDeliver(),
                                product.getStatus(),product.getImage());
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
                                                product.getCanDeliver(), product.getStatus()
                                                product.getImage()))
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

                List<Product> products = productRepository.findByStatus("Active");
                Collections.reverse(products);
                List<Favorite> userFaves = favoriteRepository.findByUser(userRepository.findByUserID(userId));

                // boolean favorited = false;
                // List<Product> favoritedProducts = products.stream()
                // .distinct()
                // .filter(userFaves::contains)
                // .collect(Collectors.toList());

                for (Product product : products) {
                        boolean favorited = false;

                        // check for user's faved products among all products
                        for (Favorite fave : userFaves) {
                                if (fave.getProduct().equals(product)) {
                                        favorited = true;
                                }
                        }

                        ViewProductDTO viewProd = new ViewProductDTO(product, favorited);

                        viewProducts.add(viewProd);

                }
                return viewProducts;

        }

        // get all products with ViewProductDTO according to user's favorites
        @Override
        public List<ViewProductDTO> getViewProductsByUser(Integer userId) {
                List<ViewProductDTO> sellerProducts = new ArrayList<ViewProductDTO>();

                User seller = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + userId));

                List<Favorite> userFaves = favoriteRepository.findByUser(userRepository.findByUserID(userId));

                List<Product> products = productRepository.findBySeller(seller);

                // not the most efficient
                for (Product product : products) {
                        boolean favorited = false;

                        // check for user's faved products among all products
                        for (Favorite fave : userFaves) {
                                if (fave.getProduct().equals(product)) {
                                        favorited = true;
                                }
                        }
                        ViewProductDTO newProd = new ViewProductDTO(product, favorited);
                        sellerProducts.add(newProd);
                }

                return sellerProducts;
        }

        @Override
        public List<ViewProductDTO> getViewProductsByCategory(String category, Integer userId) {
        // Retrieve all products for the given category
        List<Product> products = productRepository.findByCategoryName(category);
        
        // Fetch the user's favorites
        List<Favorite> userFaves = favoriteRepository.findByUser(userRepository.findByUserID(userId));
        
        // Convert products to ViewProductDTO and mark as favorite if applicable
        return products.stream()
                .map(product -> {
                        boolean isFavorited = userFaves.stream()
                                .anyMatch(fave -> fave.getProduct().equals(product));
                        return new ViewProductDTO(product, isFavorited);
                })
                .collect(Collectors.toList());
        }

        @Override
        public List<ViewProductDTO> searchViewProducts(String searchTerm, Integer userId) {
        // Retrieve all products for the user
        List<ViewProductDTO> allProducts = getViewProducts(userId);

        // Filter products based on the search term
        return allProducts.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(searchTerm.toLowerCase())
                        || (product.getDescription() != null && product.getDescription().toLowerCase().contains(searchTerm.toLowerCase())))
                .collect(Collectors.toList());
        }

        @Override
        public void uploadProductImage(MultipartFile file, Integer productId) throws IOException {
                validationService.validateImageFile(file);

                Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
                product.setImage(file.getBytes());
                productRepository.save(product);
        }
}