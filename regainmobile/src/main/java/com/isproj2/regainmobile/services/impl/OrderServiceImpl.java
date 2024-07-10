package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.repo.OrderRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        User buyer = userRepository.findById(orderDTO.getBuyerID())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Product product = productRepository.findById(orderDTO.getProductID())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Address address = addressRepository.findById(orderDTO.getAddressID())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = new Order(orderDTO, buyer, address, product);
        order.setOrderDate(LocalDateTime.now()); // Set current timestamp for order date
        orderRepository.save(order);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCurrentStatus(status);
        orderRepository.save(order);

        return new OrderDTO(order.getOrderID(), order.getProduct().getProductID(),
                order.getBuyer().getId(), order.getOrderDate(), order.getDeliveryMethod(),
                order.getDeliveryDate(), order.getPaymentMethod(), order.getTotalAmount(),
                order.getCurrentStatus(), order.getAddress().getAddressID());
    }

    @Override
    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderDTO(order.getOrderID(), order.getProduct().getProductID(),
                order.getBuyer().getId(), order.getOrderDate(), order.getDeliveryMethod(),
                order.getDeliveryDate(), order.getPaymentMethod(), order.getTotalAmount(),
                order.getCurrentStatus(), order.getAddress().getAddressID());
    }
}