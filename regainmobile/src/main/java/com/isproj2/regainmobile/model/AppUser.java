package com.isproj2.regainmobile.model;

import java.math.BigDecimal;

import com.isproj2.regainmobile.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    // @Column(name = "role_type")
    // private Long role;
    @ManyToOne
    @JoinColumn(name = "role_type") // ** needs to be 'role_type' column as FK column in DB **
    private Role role; // property name must match with 'mappedBy' name in Role class

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @lombok.NonNull
    @Column(name = "user_name")
    private String username;

    @Column(name = "contact_number", unique = true)
    private String contactNumber;

    @lombok.NonNull
    @Column(name = "pass")
    private String password;

    @Email
    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "acc_status")
    private String accountStatus = "Active";

    @Column(name = "penalty_points")
    private int penaltyPoints = 0;

    @Column(name = "commission_balance")
    private BigDecimal commissionBalance = new BigDecimal(0.0);

    // @Lob
    // @Column(name = "profile_picture", columnDefinition = "BLOB", nullable = true)
    // private byte[] image;

    @Column(name = "js_name", nullable = true)
    private String junkshopName;

    public AppUser(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.lastName = userDTO.getLastName();
        this.firstName = userDTO.getFirstName();
        this.username = userDTO.getUsername();
        this.contactNumber = userDTO.getContactNumber();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.accountStatus = userDTO.getAccountStatus();
        this.penaltyPoints = userDTO.getPenaltyPoints();
        this.commissionBalance = userDTO.getCommissionBalance();
        this.junkshopName = userDTO.getJunkshopName();
    }

    public AppUser(String username, String password, String contactNumber) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}