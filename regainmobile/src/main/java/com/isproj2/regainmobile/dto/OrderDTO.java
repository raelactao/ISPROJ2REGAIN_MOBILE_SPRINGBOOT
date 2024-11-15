package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.cglib.core.Local;

import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer orderID;

    // private Integer productID;
    private ViewProductDTO product;
    // viewproduct (prodID, sellerUsername)

    // private Integer buyerID;
    // buyerUsername
    private String buyerUsername;

    private LocalDateTime orderDate;

    @lombok.NonNull
    private String deliveryMethod;

    @lombok.NonNull
    private LocalDate deliveryDate;

    private PaymentDTO paymentMethod;

    @lombok.NonNull
    private String totalAmount;

    @lombok.NonNull
    private String currentStatus;

    // private Integer addressID;
    private AddressDTO address;
    // Address address

    public OrderDTO(Order order) {
        this.orderID = order.getOrderID();
        this.buyerUsername = order.getBuyer().getUsername();
        this.orderDate = order.getOrderDate();
        this.deliveryMethod = order.getDeliveryMethod();
        this.deliveryDate = order.getDeliveryDate();
        this.paymentMethod = new PaymentDTO(order.getPaymentMethod());
        this.totalAmount = order.getTotalAmount().toString();
        this.currentStatus = order.getCurrentStatus();
        this.address = new AddressDTO(order.getAddress());
    }
}
