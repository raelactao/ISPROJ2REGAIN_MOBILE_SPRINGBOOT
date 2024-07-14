package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import com.isproj2.regainmobile.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    // make sure the properties of the receiving class in the controller
    // matches the Json instance properties / object

    private Integer id;

    @lombok.NonNull
    private String role;

    private String lastName;

    private String firstName;

    @lombok.NonNull
    private String username;

    @lombok.NonNull
    private String contactNumber;

    @lombok.NonNull
    private String password;

    private String email;

    private String accountStatus = "Active";

    private int penaltyPoints = 0;

    private BigDecimal commissionBalance = new BigDecimal(0.0);

    // private byte[] image;

    private String junkshopName;

    public UserDTO(User user) {
        this.id = user.getUserID();
        this.role = user.getRole().getName();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.username = user.getUsername();
        this.contactNumber = user.getContactNumber();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.accountStatus = user.getAccountStatus();
        this.penaltyPoints = user.getPenaltyPoints();
        this.commissionBalance = user.getCommissionBalance();
        this.junkshopName = user.getJunkshopName();
    }
}
