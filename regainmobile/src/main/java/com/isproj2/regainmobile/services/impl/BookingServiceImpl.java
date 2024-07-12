package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.BookingDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Booking;
import com.isproj2.regainmobile.model.Equipment;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.BookingRepository;
import com.isproj2.regainmobile.repo.EquipmentRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;


    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
            User rentee = userRepository.findById(bookingDTO.getRenteeID())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Rentee not found with id" + bookingDTO.getRenteeID()));

            Equipment equipment = equipmentRepository.findById(bookingDTO.getEquipmentID())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Rentee not found with id" + bookingDTO.getEquipmentID()));
            
            Booking booking = new Booking(bookingDTO, equipment, rentee);
            bookingRepository.save(booking);
            return bookingDTO;
    }

    @Override
    public BookingDTO updateBooking(Integer bookingId, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                                    "Booking not found with id " + bookingId));

        User rentee = userRepository.findById(bookingDTO.getRenteeID())
                    .orElseThrow(() -> new ResourceNotFoundException(
                                    "Rentee not found with id" + bookingDTO.getRenteeID()));
        
        Equipment equipment = equipmentRepository.findById(bookingDTO.getEquipmentID())
                    .orElseThrow(() -> new ResourceNotFoundException(
                                    "Rentee not found with id" + bookingDTO.getEquipmentID()));
        
        booking.setRentee(rentee);
        booking.setEquipment(equipment);
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setIsAccepted(bookingDTO.getIsAccepted());

        bookingRepository.save(booking);
        return bookingDTO;
    }

    @Override
    public BookingDTO getBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                                    "Booking not found with id " + bookingId));

        return new BookingDTO(booking.getBookingID(), booking.getEquipment().getEquipmentID(),
                        booking.getStartDate(), booking.getEndDate(), booking.getRentee().getUserID(),
                        booking.getIsAccepted());
    }

    @Override
    public List<BookingDTO> getAllBookings() {
       return bookingRepository.findAll().stream()
                        .map(booking -> new BookingDTO(booking.getBookingID(),
                                        booking.getEquipment().getEquipmentID(),
                                        booking.getStartDate(), booking.getEndDate(),
                                        booking.getRentee().getUserID(),
                                        booking.getIsAccepted()))
                        .collect(Collectors.toList());
    }
    
}
