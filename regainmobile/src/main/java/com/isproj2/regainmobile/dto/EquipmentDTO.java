package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {


    private Integer equipmentID;

    private Integer renterID;

    @lombok.NonNull
    private String equipmentName;

    private String description;

    @lombok.NonNull
    private Integer location;

    private Integer categoryID;

    @lombok.NonNull
    private BigDecimal price;

    // private byte[] image;
    
}
