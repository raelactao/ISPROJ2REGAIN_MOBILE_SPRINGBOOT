package com.isproj2.regainmobile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    private String newStatus;
    private Integer updatedByUserID;
}
