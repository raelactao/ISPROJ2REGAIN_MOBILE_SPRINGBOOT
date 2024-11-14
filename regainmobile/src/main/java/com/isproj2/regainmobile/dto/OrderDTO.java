package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.cglib.core.Local;

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

    private Integer productID;

    private Integer buyerID;

    private LocalDateTime orderDate;

    @lombok.NonNull
    private String deliveryMethod;

    @lombok.NonNull
    private LocalDate deliveryDate;

    private Integer paymentMethod;

    @lombok.NonNull
    private BigDecimal totalAmount;

    @lombok.NonNull
    private String currentStatus;

    private Integer addressID;
}
