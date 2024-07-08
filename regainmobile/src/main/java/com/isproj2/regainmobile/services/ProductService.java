package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Integer productId, ProductDTO productDTO);
    void deleteProduct(Integer productId);
    ProductDTO getProductById(Integer productId);
    List<ProductDTO> getAllProducts();
}
