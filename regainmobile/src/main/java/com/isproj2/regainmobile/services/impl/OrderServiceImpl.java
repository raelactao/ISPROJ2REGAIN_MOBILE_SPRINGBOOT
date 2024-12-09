package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.dto.OrderLogDTO;
import com.isproj2.regainmobile.dto.PaymentDTO;
import com.isproj2.regainmobile.dto.ViewOfferDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.OrderLog;
import com.isproj2.regainmobile.model.Payment;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.repo.OfferRepository;
import com.isproj2.regainmobile.repo.OrderLogRepository;
import com.isproj2.regainmobile.repo.OrderRepository;
import com.isproj2.regainmobile.repo.PaymentRepository;
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

        @Autowired
        private OrderLogRepository orderLogRepository;

        @Autowired
        private PaymentRepository paymentRepository;

        // @Autowired
        // private OfferRepository offerRepository;

        @Override
        @Transactional
        public OrderDTO createOrder(OrderDTO orderDTO) {
                User buyer = userRepository.findByUsername(orderDTO.getBuyerUsername())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Buyer not found with id " + orderDTO.getBuyerUsername()));

                Product product = productRepository.findById(orderDTO.getProduct().getProductID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + orderDTO.getProduct().getProductID()));

                Integer productID = product.getLocation().getAddressID();

                // Payment payment =
                // paymentRepository.findById(orderDTO.getPaymentMethod().getId())
                // .orElseThrow(() -> new ResourceNotFoundException(
                // "Payment not found with id " + orderDTO.getPaymentMethod()));

                Payment payment = new Payment(orderDTO.getPaymentMethod());
                if (payment.getPaymentType() == "GCash") {

                }
                paymentRepository.save(payment);

                Address address = new Address();
                if (orderDTO.getDeliveryMethod().equals("Buyer Pick-up")) {
                        address = product.getLocation();
                } else if (orderDTO.getDeliveryMethod().equals("Seller Drop-off")) {
                        address = addressRepository.findByAddressID(orderDTO.getAddress().getAddressID())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Address not found with id "
                                                                        + orderDTO.getAddress().getAddressID()));
                }

                Order order = new Order(orderDTO, buyer, address, product, payment);
                order.setOrderDate(Date.valueOf(LocalDate.now())); // Set current timestamp for order date
                order.setCommissionFee(computeCommissionFee(order));
                orderRepository.save(order);
                product.setStatus("Ordered");
                productRepository.save(product);

                return orderDTO;
        }

        public BigDecimal computeCommissionFee(Order order) {

                BigDecimal commFee;
                BigDecimal percentage;

                switch (order.getProduct().getCategory().getName()) {
                        case "Metal":
                                percentage = new BigDecimal(0.03);
                                break;
                        case "Plastic":
                                percentage = new BigDecimal(0.025);
                                break;
                        case "Paper":
                                percentage = new BigDecimal(0.02);
                                break;
                        case "Glass":
                                percentage = new BigDecimal(0.02);
                                break;
                        case "Electronics":
                                percentage = new BigDecimal(0.03);
                                break;
                        default:
                                percentage = null;
                }

                if (percentage != null) {
                        commFee = order.getTotalAmount().multiply(percentage);
                } else {
                        commFee = null;
                }
                return commFee;
        }

        @Override
        @Transactional
        public OrderDTO updateOrderStatus(Integer orderId, String newStatus, Integer updatedByUserID) {
                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new RuntimeException("Order not found"));

                User updatedByUser = userRepository.findById(updatedByUserID)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Log the previous status
                String prevStatus = order.getCurrentStatus();

                // Update current status
                order.setCurrentStatus(newStatus);
                orderRepository.save(order);

                // Create and save the OrderLog
                OrderLog orderLog = new OrderLog();
                orderLog.setOrder(order);
                orderLog.setPrevStatus(prevStatus);
                orderLog.setDeliveryDate(order.getDeliveryDate());
                orderLog.setUpdatedByUser(updatedByUser);
                orderLog.setTimeStamp(LocalDateTime.now());

                orderLogRepository.save(orderLog);

                return convertToOrderDTO(order);

        }

        // being currently used
        @Override
        @Transactional
        public OrderDTO updateOrderStatus(OrderDTO orderDTO, Integer updatedByUserID) {
                Order order = orderRepository.findById(orderDTO.getOrderID())
                                .orElseThrow(() -> new RuntimeException("Order not found"));

                User updatedByUser = userRepository.findById(updatedByUserID)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Log the previous status
                String prevStatus = order.getCurrentStatus();

                // Update current status
                order.setCurrentStatus(orderDTO.getCurrentStatus());
                Order updatedOrder = orderRepository.save(order);

                // Create and save the OrderLog
                OrderLog orderLog = new OrderLog();
                orderLog.setOrder(order);
                orderLog.setPrevStatus(prevStatus);
                orderLog.setDeliveryDate(order.getDeliveryDate());
                orderLog.setUpdatedByUser(updatedByUser);
                orderLog.setTimeStamp(LocalDateTime.now());

                orderLogRepository.save(orderLog);

                return new OrderDTO(updatedOrder);
        }

        private OrderDTO convertToOrderDTO(Order order) {
                return new OrderDTO(
                                order.getOrderID(),
                                new ViewProductDTO(order.getProduct(), false),
                                order.getBuyer().getUsername(),
                                order.getOrderDate(),
                                order.getDeliveryMethod(),
                                order.getDeliveryDate(),
                                new PaymentDTO(order.getPaymentMethod()),
                                order.getTotalAmount().toString(),
                                order.getCommissionFee().toString(),
                                order.getCurrentStatus(),
                                new AddressDTO(order.getAddress()));
        }

        @Override
        public OrderDTO getOrderById(Integer orderId) {
                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new RuntimeException("Order not found"));

                return new OrderDTO(order.getOrderID(), new ViewProductDTO(order.getProduct(), false),
                                order.getBuyer().getUsername(), order.getOrderDate(), order.getDeliveryMethod(),
                                order.getDeliveryDate(), new PaymentDTO(order.getPaymentMethod()),
                                order.getTotalAmount().toString(), order.getCommissionFee().toString(),
                                order.getCurrentStatus(), new AddressDTO(order.getAddress()));
        }

        @Override
        public List<OrderDTO> getOrdersByBuyer(Integer buyerId) {
                User buyer = userRepository.findById(buyerId)
                                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + buyerId));
                List<Order> orders = orderRepository.findByBuyer(buyer);
                return orders.stream().map(this::convertToOrderDTO).collect(Collectors.toList());
        }

        @Override
        public List<OrderDTO> getOrdersBySeller(Integer sellerID) {
                List<Order> orders = orderRepository.findByProductSellerUserID(sellerID);
                return orders.stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<OrderLogDTO> getOrderLogsByOrderId(Integer orderId) {
                List<OrderLog> orderLogs = orderLogRepository.findByOrderOrderID(orderId);
                return orderLogs.stream()
                                .map(this::convertTLogDTO)
                                .collect(Collectors.toList());
        }

        private OrderLogDTO convertTLogDTO(OrderLog log) {
                OrderLogDTO dto = new OrderLogDTO();
                dto.setTrackingID(log.getTrackingID());
                dto.setOrderID(log.getOrder().getOrderID());
                dto.setDeliveryDate(log.getDeliveryDate());
                dto.setPrevStatus(log.getPrevStatus());
                dto.setUpdatedByUserID(log.getUpdatedByUser().getUserID());
                dto.setTimeStamp(log.getTimeStamp());

                return dto;
        }

        private OrderDTO convertToDTO(Order order) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderID(order.getOrderID());
                // orderDTO.setProductID(order.getProduct().getProductID());
                orderDTO.setProduct(new ViewProductDTO(order.getProduct(), false));
                // orderDTO.setBuyerID(order.getBuyer().getUserID());
                orderDTO.setBuyerUsername(order.getBuyer().getUsername());
                orderDTO.setOrderDate(order.getOrderDate());
                orderDTO.setDeliveryMethod(order.getDeliveryMethod());
                orderDTO.setDeliveryDate(order.getDeliveryDate());
                orderDTO.setPaymentMethod(new PaymentDTO(order.getPaymentMethod()));
                orderDTO.setTotalAmount(order.getTotalAmount().toString());
                orderDTO.setCurrentStatus(order.getCurrentStatus());
                orderDTO.setAddress(new AddressDTO(order.getAddress()));
                // orderDTO.setAddressID(order.getAddress().getAddressID());
                return orderDTO;
        }

        // @Override
        // public List<OrderDTO> getOrdersByDeliveryBuyer(String deliveryMethod, Integer
        // userId) {

        // Boolean verified = false;

        // // if (deliveryMethod == ("Seller Drop-off") || deliveryMethod == ("Buyer
        // // Pick-up")) {
        // // verified = true;
        // // }

        // User buyer = userRepository.findById(userId)
        // .orElseThrow(() -> new ResourceNotFoundException("Buyer not found with id " +
        // userId));
        // List<Order> orderList = orderRepository.findByBuyer(buyer);
        // List<OrderDTO> dtoList = new ArrayList<OrderDTO>();

        // for (Order order : orderList) {
        // if (order.getDeliveryMethod().equals(deliveryMethod)) {
        // dtoList.add(new OrderDTO(order));
        // }

        // }

        // return dtoList;

        // }

        // @Override
        // public List<OrderDTO> getOrdersByDeliverySeller(String deliveryMethod,
        // Integer userId) {
        // Boolean verified = false;

        // // if (deliveryMethod.equals("Seller Drop-off") ||
        // deliveryMethod.equals("Buyer
        // // Pick-up")) {
        // // verified = true;
        // // }

        // User seller = userRepository.findById(userId)
        // .orElseThrow(() -> new ResourceNotFoundException("Buyer not found with id " +
        // userId));
        // List<Order> orderList =
        // orderRepository.findByProductSellerUserID(seller.getUserID());
        // List<OrderDTO> dtoList = new ArrayList<OrderDTO>();

        // for (Order order : orderList) {
        // if (order.getDeliveryMethod().equals(deliveryMethod)) {
        // dtoList.add(new OrderDTO(order));
        // }

        // }

        // return dtoList;
        // }
}