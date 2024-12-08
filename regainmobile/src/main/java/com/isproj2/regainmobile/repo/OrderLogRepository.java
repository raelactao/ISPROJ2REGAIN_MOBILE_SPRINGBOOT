package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.OrderLog;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {

    List<OrderLog> findByOrderOrderID(Integer orderID);
}
