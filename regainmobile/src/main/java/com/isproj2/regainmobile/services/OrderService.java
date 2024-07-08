package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrderStatus(Integer orderId, String status);
    OrderDTO getOrderById(Integer orderId);
}
