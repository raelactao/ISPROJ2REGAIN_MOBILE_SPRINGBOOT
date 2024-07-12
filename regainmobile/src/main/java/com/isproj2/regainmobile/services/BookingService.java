package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.BookingDTO;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(Integer bookingId, BookingDTO bookingDTO);
    BookingDTO getBookingById(Integer bookingId);
    List<BookingDTO> getAllBookings();
}
