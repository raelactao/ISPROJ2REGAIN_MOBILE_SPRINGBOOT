package com.isproj2.regainmobile.dto;

// import java.math.BigDecimal;
import java.math.MathContext;

import com.isproj2.regainmobile.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
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

    private String email;

    @lombok.NonNull
    private String password;

    private String accountStatus;

    private int penaltyPoints;

    // private byte[] image;

    private String junkshopName;

    public UserDTO(User user) {
        this.id = user.getUserID();
        this.role = user.getRole().getName();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.accountStatus = user.getAccountStatus();
        this.penaltyPoints = user.getPenaltyPoints();
        this.junkshopName = user.getJunkshopName();
    }
}
