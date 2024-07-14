package com.isproj2.regainmobile.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.isproj2.regainmobile.dto.OrderLogDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="orderlog")
@Entity
public class OrderLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private Integer trackingID;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", unique = true)
    private Order order;

    @lombok.NonNull
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @lombok.NonNull
    @Column(name = "prev_status")
    private String prevStatus;
    
    @ManyToOne
    @JoinColumn(name = "updated_by_user", referencedColumnName = "user_id", nullable = false)
    private User updatedByUser; 
    
    @lombok.NonNull
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    public OrderLog(OrderLogDTO orderLogDTO, Order order, User updatedByUser) {
        this.trackingID = orderLogDTO.getTrackingID();
        this.order = order;
        this.deliveryDate = orderLogDTO.getDeliveryDate();
        this.prevStatus = orderLogDTO.getPrevStatus();
        this.updatedByUser = updatedByUser;
        this.timeStamp = orderLogDTO.getTimeStamp();
    }
}
