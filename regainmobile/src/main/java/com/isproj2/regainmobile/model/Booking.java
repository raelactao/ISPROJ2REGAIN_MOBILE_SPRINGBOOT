package com.isproj2.regainmobile.model;

import java.util.Date;

import com.isproj2.regainmobile.dto.BookingDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "booking")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingID;

    @ManyToOne
    @JoinColumn(name = "equipment", referencedColumnName = "equipment_id", nullable = false)
    private Equipment equipment;

    @lombok.NonNull
    @Column(name = "start_date")
    private Date startDate;

    @lombok.NonNull
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "rentee_id", referencedColumnName = "user_id", nullable = false)
    private User rentee;

    @lombok.NonNull
    @Column(name = "is_accepted")
    private Boolean isAccepted;

    public Booking(BookingDTO bookingDTO, Equipment equipment, User rentee) {
        this.bookingID = bookingDTO.getBookingID();
        this.equipment = equipment;
        this.startDate = bookingDTO.getStartDate();
        this.endDate = bookingDTO.getEndDate();
        this.rentee = rentee;
        this.isAccepted = bookingDTO.getIsAccepted();
    }

}
