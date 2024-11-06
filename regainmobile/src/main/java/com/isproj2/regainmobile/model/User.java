package com.isproj2.regainmobile.model;

import java.math.BigDecimal;

import java.util.Collection;

import com.isproj2.regainmobile.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userID;

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

    @lombok.NonNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @lombok.NonNull
    @Column(name = "pass", nullable = false)
    private String password;

    @Column(name = "acc_status", columnDefinition = "default 'Pending'")
    private String accountStatus = "Pending";

    @Column(name = "penalty_points")
    private int penaltyPoints = 0;

    // @Lob
    // @Column(name = "profile_picture", columnDefinition = "BLOB", nullable = true)
    // private byte[] image;

    @Column(name = "js_name", nullable = true)
    private String junkshopName;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Collection<Product> product;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Favorite> favorite;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Address> address;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Collection<Order> order;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Collection<Offer> offer;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Collection<Offer> offer2;

    @OneToMany(mappedBy = "reporter", fetch = FetchType.EAGER)
    private Collection<ListingReport> listingReport;

    @OneToMany(mappedBy = "reporter", fetch = FetchType.EAGER)
    private Collection<UserReport> userReport;

    @OneToMany(mappedBy = "reportedUser", fetch = FetchType.EAGER)
    private Collection<UserReport> userReport2;

    @OneToMany(mappedBy = "updatedByUser", fetch = FetchType.EAGER)
    private Collection<OrderLog> orderLog;

    public User(UserDTO userDTO, Role role) {
        this.userID = userDTO.getId();
        this.lastName = userDTO.getLastName();
        this.firstName = userDTO.getFirstName();
        this.username = userDTO.getUsername();
        this.role = role;
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.accountStatus = userDTO.getAccountStatus();
        this.penaltyPoints = userDTO.getPenaltyPoints();
        this.junkshopName = userDTO.getJunkshopName();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
