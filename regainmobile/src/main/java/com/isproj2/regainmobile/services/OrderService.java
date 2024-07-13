package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.model.User;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrderStatus(Integer orderId, String newStatus, Integer updatedByUserID);
    OrderDTO getOrderById(Integer orderId);
}
