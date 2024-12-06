package com.isproj2.regainmobile.model;

import java.math.BigDecimal;

import com.isproj2.regainmobile.dto.PaymentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount_paid", columnDefinition = "Decimal(19,2)")
    private BigDecimal amountPaid;

    @Column(name = "reference_number", length = 30)
    private String referenceNumber;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "remarks", length = 100)
    private String remarks;

    public Payment(PaymentDTO dto) {
        this.id = dto.getId();
        this.paymentType = dto.getPaymentType();
        this.amountPaid = new BigDecimal(dto.getAmountPaid());
        this.referenceNumber = dto.getReferenceNumber();
        this.status = dto.getStatus();
        this.remarks = dto.getRemarks();
    }

}
