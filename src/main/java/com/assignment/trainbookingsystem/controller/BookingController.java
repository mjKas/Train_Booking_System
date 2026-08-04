package com.assignment.trainbookingsystem.controller;

import com.assignment.trainbookingsystem.dto.AvailableSeatDto;
import com.assignment.trainbookingsystem.dto.BookingRequest;
import com.assignment.trainbookingsystem.dto.BookingResponse;
import com.assignment.trainbookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // Example URL: GET /api/bookings/available?scheduleId=123...&startStationOrder=1&endStationOrder=3
    @GetMapping("/available")
    public ResponseEntity<List<AvailableSeatDto>> getAvailableSeats(
            @RequestParam UUID scheduleId,
            @RequestParam int startStationOrder,
            @RequestParam int endStationOrder) {

        List<AvailableSeatDto> availableSeats = bookingService.getAvailableSeats(scheduleId, startStationOrder, endStationOrder);
        return ResponseEntity.ok(availableSeats);
    }

    // Example URL: POST /api/bookings
    @PostMapping
    public ResponseEntity<BookingResponse> bookSeat(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookSeat(request);
        return ResponseEntity.ok(response);
    }
}