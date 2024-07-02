package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Custom queries can be defined here if needed
}
