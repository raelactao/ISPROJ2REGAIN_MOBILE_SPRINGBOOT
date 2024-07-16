package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.dto.ViewOfferDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrderStatus(Integer orderId, String newStatus, Integer updatedByUserID);
    OrderDTO getOrderById(Integer orderId);
    List<OrderDTO> getOrdersByBuyer(Integer buyerId);
    List<OrderDTO> getOrdersBySeller(Integer sellerID);
}
