package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Custom queries can be defined here if needed
    List<Product> findBySeller(User seller);

    List<Product> findByLocationAddressID(Integer id);

    List<Product> findByStatus(String status);

    List<Product> findByCategoryName(String categoryName);

    @Query("SELECT p FROM Product p WHERE p.category.name = :category AND p.status = :status")
    List<Product> findByCategoryNameAndStatus(@Param("category") String category, @Param("status") String status);

}
