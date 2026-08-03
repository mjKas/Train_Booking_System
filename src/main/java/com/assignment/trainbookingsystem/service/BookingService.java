package com.assignment.trainbookingsystem.service;
import com.assignment.trainbookingsystem.dto.AvailableSeatDto;
import com.assignment.trainbookingsystem.dto.BookingRequest;
import com.assignment.trainbookingsystem.dto.BookingResponse;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    // Finds seats that are available for the ENTIRE requested journey
    List<AvailableSeatDto> getAvailableSeats(UUID scheduleId, int startStationOrder, int endStationOrder);

    // Executes the transaction with row-level database locking
    BookingResponse bookSeat(BookingRequest request);
}