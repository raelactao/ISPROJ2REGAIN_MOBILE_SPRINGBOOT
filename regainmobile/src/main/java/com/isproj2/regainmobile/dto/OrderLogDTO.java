package com.isproj2.regainmobile.dto;

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
public class OrderLogDTO {

    private Integer trackingID;

    private Integer orderID;

    @lombok.NonNull
    private LocalDate deliveryDate;

    @lombok.NonNull
    private String prevStatus;
    
    private Integer updatedByUserID; 
    
    @lombok.NonNull
    private LocalDateTime timeStamp;
}
