package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.isproj2.regainmobile.model.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {

    // make sure the properties of the receiving class in the controller
    // matches the Json instance properties / object

    private Integer id;

    private String role;

    private String lastName;

    private String firstName;

    @lombok.NonNull
    private String username;

    private String contactNumber;

    @lombok.NonNull
    private String password;

    private String email;

    private String accountStatus = "Active";

    private int penaltyPoints = 0;

    private BigDecimal commissionBalance = new BigDecimal(0.0);

    // private byte[] image;

    private String junkshopName;

    public UserDTO(AppUser user) {
        this.id = user.getId();
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

    public UserDTO(String username, String password, String contactNumber) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
    }

}
