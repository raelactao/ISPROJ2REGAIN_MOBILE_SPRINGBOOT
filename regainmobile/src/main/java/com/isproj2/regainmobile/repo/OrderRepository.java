package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByBuyer(User buyer);

    List<Order> findByProductSellerUserID(Integer sellerId);

}
