package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.dto.OrderLogDTO;
import com.isproj2.regainmobile.dto.OrderStatusUpdateRequest;
import com.isproj2.regainmobile.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @RequestBody OrderDTO orderDTO, @PathVariable Integer userId) {

        OrderDTO updatedOrderDTO = orderService.updateOrderStatus(orderDTO, userId);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Integer orderId) {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByBuyer(@PathVariable Integer buyerId) {
        List<OrderDTO> orders = orderService.getOrdersByBuyer(buyerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/logs/{orderId}")
    public ResponseEntity<List<OrderLogDTO>> getOrderLogsByOrder(@PathVariable Integer orderId) {
        List<OrderLogDTO> orders = orderService.getOrderLogsByOrderId(orderId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersBySeller(@PathVariable Integer sellerId) {
        List<OrderDTO> orders = orderService.getOrdersBySeller(sellerId);
        return ResponseEntity.ok(orders);
    }
}