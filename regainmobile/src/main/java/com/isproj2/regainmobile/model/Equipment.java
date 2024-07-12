package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.util.Collection;

import com.isproj2.regainmobile.dto.EquipmentDTO;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "equipments")
@Entity
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Integer equipmentID;

    @ManyToOne
    @JoinColumn(name = "renter_id", referencedColumnName = "user_id", nullable = false)
    private User renter;

    @lombok.NonNull
    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "descript")
    private String description;

    @lombok.NonNull
    @Column(name = "location")
    private Integer location;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @lombok.NonNull
    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "reportedEQListing", fetch = FetchType.EAGER)
    private Collection<EQListingReport> eqlistingReport;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private Collection<Booking> booking;

    // @Lob
    // @Column(name = "image")
    // private byte[] image;

    public Equipment(EquipmentDTO equipmentDTO, User renter, Category category) {
        this.equipmentID = equipmentDTO.getEquipmentID();
        this.renter = renter;
        this.equipmentName = equipmentDTO.getEquipmentName();
        this.description = equipmentDTO.getDescription();
        this.location = equipmentDTO.getLocation();
        this.category = category;
        this.price = equipmentDTO.getPrice();
        //this.image = equipmentDTO.getImage();
    }
}
