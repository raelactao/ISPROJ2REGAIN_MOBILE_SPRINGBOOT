package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @lombok.NonNull
    private LocalDateTime orderDate;

    @lombok.NonNull
    private String deliveryMethod;

    @lombok.NonNull
    private LocalDate deliveryDate;

    @lombok.NonNull
    private Integer paymentMethod;

    @lombok.NonNull
    private BigDecimal totalAmount;

    @lombok.NonNull
    private String currentStatus;

    private Integer addressID;    
}
