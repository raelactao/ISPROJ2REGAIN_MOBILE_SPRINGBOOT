package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import com.isproj2.regainmobile.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private Long id;

    @lombok.NonNull
    private String role;

    private String lastName;

    private String firstName;

    @lombok.NonNull
    private String username;

    @lombok.NonNull
    private String contactNo;

    @lombok.NonNull
    private String password;

    private String email;

    private String accountStatus = "Active";

    private int penaltyPoints = 0;

    private BigDecimal commissionBalance = new BigDecimal(0.0);

    // private byte[] image;

    private String junkshopName;

    public UserDTO(User user) {
        this.id = user.getId();
        this.role = user.getRole().getName();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.username = user.getUsername();
        this.contactNo = user.getContactNo();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.accountStatus = user.getAccountStatus();
        this.penaltyPoints = user.getPenaltyPoints();
        this.commissionBalance = user.getCommissionBalance();
        this.junkshopName = user.getJunkshopName();
    }
}
