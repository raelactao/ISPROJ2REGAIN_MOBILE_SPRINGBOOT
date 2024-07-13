package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import com.isproj2.regainmobile.dto.OrderDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderID;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", unique = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id", nullable = false)
    private User buyer;

    @lombok.NonNull
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @lombok.NonNull
    @Column(name = "delivery_method")
    private String deliveryMethod;

    @lombok.NonNull
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @lombok.NonNull
    @Column(name = "payment_method")
    private Integer paymentMethod;

    @lombok.NonNull
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @lombok.NonNull
    @Column(name = "current_status")
    private String currentStatus;

    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Collection<OrderLog> orderLog;


    public Order(OrderDTO orderDTO, User user, Address address, Product product) {
        this.orderID = orderDTO.getOrderID();
        this.product = product;
        this.buyer = user;
        this.orderDate = orderDTO.getOrderDate();
        this.deliveryMethod = orderDTO.getDeliveryMethod();
        this.deliveryDate = orderDTO.getDeliveryDate();
        this.paymentMethod = orderDTO.getPaymentMethod();
        this.totalAmount = orderDTO.getTotalAmount();
        this.currentStatus = orderDTO.getCurrentStatus();
        this.address = address;
    }
}
