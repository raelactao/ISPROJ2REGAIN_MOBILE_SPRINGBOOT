package com.isproj2.regainmobile.dto;

import java.util.List;

import com.isproj2.regainmobile.model.Commissions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionsTotalDTO {

    private String totalBal;

    private List<CommissionsDTO> commsList;

    // public CommissionsTotalDTO(List<Commisisons>) {

    // }

}
