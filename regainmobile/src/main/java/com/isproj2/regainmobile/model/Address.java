package com.isproj2.regainmobile.model;

import java.util.Collection;

import com.isproj2.regainmobile.config.EncryptionUtil;
import com.isproj2.regainmobile.dto.AddressDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressID;

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "unit_number", nullable = true)
    private String unitNumber;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "street")
    private String street;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "barangay")
    private String barangay;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "city")
    private String city;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "province")
    private String province;

    @Convert(converter = EncryptionUtil.class)
    @lombok.NonNull
    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Collection<Order> order;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Collection<Product> products;

    public Address(AddressDTO addressDTO, User user) {
        this.addressID = addressDTO.getAddressID();
        this.unitNumber = addressDTO.getUnitNumber();
        this.street = addressDTO.getStreet();
        this.barangay = addressDTO.getBarangay();
        this.city = addressDTO.getCity();
        this.province = addressDTO.getProvince();
        this.zipCode = addressDTO.getZipCode();
        this.user = user;
    }
}
