package com.isproj2.regainmobile.model;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.beans.factory.annotation.Value;

import com.isproj2.regainmobile.config.EncryptionUtil;
import com.isproj2.regainmobile.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.groups.ConvertGroup;
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

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "last_name")
    private String lastName;

    @Convert(converter = EncryptionUtil.class)
    @Convert(converter = EncryptionUtil.class)
    @Column(name = "first_name")
    private String firstName;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "user_name")
    private String username;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    // will be hashed rather than encrypted
    @lombok.NonNull
    @Column(name = "pass", nullable = false, length = 255)
    private String password;

    @Column(name = "acc_status", columnDefinition = "default 'Pending'")
    private String accountStatus = "Pending";

    @Column(name = "penalty_points")
    private int penaltyPoints = 0;

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "contact_number", length = 50)
    private String phone;

    @Lob
    @Column(name = "profile_image", columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    @Lob
    @Column(name = "gcash_qr", columnDefinition = "LONGBLOB")
    private byte[] gcashQr;

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "birthday")
    private String birthday;

    // @Lob
    // @Column(name = "profile_picture", columnDefinition = "BLOB", nullable = true)
    // private byte[] image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private UserID userIDDetails;

    @Convert(converter = EncryptionUtil.class)
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
        if (userDTO.getBirthday() != null) {
            this.birthday = userDTO.getBirthday().toString();
        }
        // this.birthday = userDTO.getBirthday().toString();
        this.junkshopName = userDTO.getJunkshopName();
        this.profileImage = userDTO.getProfileImage();
        this.gcashQr = userDTO.getGcashQRcode();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
