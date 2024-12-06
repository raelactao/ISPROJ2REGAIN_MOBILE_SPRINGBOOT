package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.beans.factory.annotation.Value;

import com.isproj2.regainmobile.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @JsonBackReference
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
    @Column(name = "pass", nullable = false, length = 255)
    private String password;

    @Value("${some.key:Pending}")
    @Column(name = "acc_status", columnDefinition = "default 'Pending'")
    private String accountStatus = "Pending";

    @Column(name = "penalty_points")
    private int penaltyPoints = 0;

    @Column(name = "contact_number", length = 50)
    private String phone;

    @Column(name = "profile_picture")
    private byte[] profileImagePath;

    @Column(name = "GCashQR")
    private byte[] gcashQR;

    @Column(name = "birthday")
    private LocalDate birthday;

    // @Lob
    // @Column(name = "profile_picture", columnDefinition = "BLOB", nullable = true)
    // private byte[] image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private UserID userIDDetails;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY)
    private Collection<ChatRoom> chatRoom;

    @JsonManagedReference
    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY)
    private Collection<ChatRoom> chatRoom2;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    private Collection<ChatMessage> chatMessage;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.EAGER)
    private Collection<ChatMessage> chatMessage2;

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
        this.phone = userDTO.getPhone();
        // this.profileImagePath = userDTO.getProfileImagePath();
        // this.gcashQR = userDTO.getGcashQR();
        this.birthday = userDTO.getBirthday();
        this.junkshopName = userDTO.getJunkshopName();
    }

    // public User(UserDTO userDTO) {
    // this.userID = userDTO.getId();
    // this.lastName = userDTO.getLastName();
    // this.firstName = userDTO.getFirstName();
    // this.username = userDTO.getUsername();
    // this.role = userDTO.getRole();
    // this.password = userDTO.getPassword();
    // this.email = userDTO.getEmail();
    // this.accountStatus = userDTO.getAccountStatus();
    // this.penaltyPoints = userDTO.getPenaltyPoints();
    // this.phone = userDTO.getPhone();
    // // this.profileImagePath = userDTO.getProfileImagePath();
    // // this.gcashQR = userDTO.getGcashQR();
    // this.birthday = userDTO.getBirthday();
    // this.junkshopName = userDTO.getJunkshopName();
    // }

    public void setRole(Role role) {
        this.role = role;
    }
}
