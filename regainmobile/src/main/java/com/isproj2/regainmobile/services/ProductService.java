package com.isproj2.regainmobile.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

    List<ViewProductDTO> getViewProductsByCategory(String category, Integer userId);

    List<ViewProductDTO> searchViewProducts(String searchTerm, Integer userId);

    void uploadProductImage(MultipartFile file, Integer productId) throws IOException;

}
