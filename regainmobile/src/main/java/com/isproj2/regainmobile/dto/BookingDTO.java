package com.isproj2.regainmobile.dto;

import java.util.Date;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private Integer bookingID;

    private Integer equipmentID;

    @lombok.NonNull
    private Date startDate;

    @lombok.NonNull
    private Date endDate;

    @ManyToOne
    private Integer renteeID;

    @lombok.NonNull
    private Boolean isAccepted;
    
}
