package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PaymentDTO {

    private Integer id;

    private String paymentType;

    @lombok.NonNull
    private String amountPaid;

    private String referenceNumber;

    private String status;

    private String remarks;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.paymentType = payment.getPaymentType();
        this.amountPaid = payment.getAmountPaid().toString();
        this.referenceNumber = payment.getReferenceNumber();
        this.status = payment.getStatus();
        this.remarks = payment.getRemarks();
    }

}
