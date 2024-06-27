package com.isproj2.regainmobile.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
// @NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "role_type")
    private Long role;
    // @ManyToOne
    // @JoinColumn(name = "role_id", nullable = false)
    // private Role role;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name")
    private String username;

    @Column(name = "contact_number", unique = true)
    private String contactNo;

    @Column(name = "pass")
    private String password;

    @Email
    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "address")
    private Long address;
    // @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade =
    // CascadeType.DETACH)
    // private Set<Address> addressList;

    /*
     * need to modify address (and users?) table -- why list of addresses when no
     * userID column?
     */

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

    public User() {
    }

    public User(String username, String contactNo, String pw) {
        this.username = username;
        this.contactNo = contactNo;
        this.password = pw;
    }

}
