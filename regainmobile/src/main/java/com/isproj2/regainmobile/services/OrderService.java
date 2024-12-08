package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.dto.OrderLogDTO;
import com.isproj2.regainmobile.dto.ViewOfferDTO;
import com.isproj2.regainmobile.model.Order;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrderStatus(Integer orderId, String newStatus, Integer updatedByUserID);

    OrderDTO updateOrderStatus(OrderDTO orderDTO, Integer updatedByUserID);

    OrderDTO getOrderById(Integer orderId);

    List<OrderLogDTO> getOrderLogsByOrderId(Integer orderId);

    List<OrderDTO> getOrdersByBuyer(Integer buyerId);

    List<OrderDTO> getOrdersBySeller(Integer sellerID);

    // List<OrderDTO> getOrdersByDeliveryBuyer(String deliveryMethod, Integer
    // userId);

    // List<OrderDTO> getOrdersByDeliverySeller(String deliveryMethod, Integer
    // userId);
}
