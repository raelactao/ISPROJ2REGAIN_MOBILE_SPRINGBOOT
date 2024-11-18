package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

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
    private Date orderDate;

    @lombok.NonNull
    @Column(name = "delivery_method", length = 50)
    private String deliveryMethod;

    @lombok.NonNull
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "payment_method", referencedColumnName = "payment_id")
    private Payment paymentMethod;

    @lombok.NonNull
    @Column(name = "total_amount", columnDefinition = "Decimal(19,2)")
    private BigDecimal totalAmount;

    @Column(name = "commission_fee", columnDefinition = "Decimal(19,2)")
    private BigDecimal commissionFee;

    @lombok.NonNull
    @Column(name = "current_status", length = 20)
    private String currentStatus;

    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "order")
    private List<Commissions> commissions;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Collection<OrderLog> orderLog;

    public Order(OrderDTO orderDTO, User user, Address address, Product product, Payment payment) {
        this.orderID = orderDTO.getOrderID();
        this.product = product;
        this.buyer = user;
        this.orderDate = orderDTO.getOrderDate();
        this.deliveryMethod = orderDTO.getDeliveryMethod();
        this.deliveryDate = orderDTO.getDeliveryDate();
        this.paymentMethod = payment;
        this.totalAmount = new BigDecimal(orderDTO.getTotalAmount());
        this.currentStatus = orderDTO.getCurrentStatus();
        this.address = address;
    }
}
