package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.model.Product;

public interface ProductService {
    Product addProduct(ProductDTO productDTO);
    Product getProductById(Integer productId);
    Product updateProduct(Integer productId, ProductDTO productDTO);
    void deleteProduct(Integer productId);
    // List<Product> getAllProducts();
}
