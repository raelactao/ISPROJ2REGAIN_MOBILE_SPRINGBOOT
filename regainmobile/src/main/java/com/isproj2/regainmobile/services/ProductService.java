package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Integer productId, ProductDTO productDTO);

    void deleteProduct(Integer productId);

    ProductDTO getProductById(Integer productId);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByUser(Integer userId);

    List<ViewProductDTO> getViewProductsByUser(Integer userId);

    List<ViewProductDTO> getViewProducts(Integer userId);
}
