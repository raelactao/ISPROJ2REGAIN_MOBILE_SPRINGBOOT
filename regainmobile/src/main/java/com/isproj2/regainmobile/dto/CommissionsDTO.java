package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Commissions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionsDTO {

    private Integer commissionID;

    private Integer userID;

    private OrderDTO order;

    private String commissionBalance;

    private String status;

    private String remarks;

    private PaymentDTO payment;

    public CommissionsDTO(Commissions comm) {
        this.commissionID = comm.getCommissionID();
        this.userID = comm.getUser().getUserID();
        this.order = new OrderDTO(comm.getOrder());
        this.commissionBalance = comm.getCommissionBalance().toString();
        this.status = comm.getStatus();
        this.remarks = comm.getRemarks();
        if (comm.getPayment() != null) {
            this.payment = new PaymentDTO(comm.getPayment());
        }
    }

}
