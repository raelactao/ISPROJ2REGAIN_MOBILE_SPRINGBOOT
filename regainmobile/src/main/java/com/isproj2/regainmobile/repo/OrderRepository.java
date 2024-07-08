package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can add custom query methods here if needed
}
