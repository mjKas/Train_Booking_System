package com.assignment.trainbookingsystem.service.impl;

import com.assignment.trainbookingsystem.dto.AvailableSeatDto;
import com.assignment.trainbookingsystem.dto.BookingRequest;
import com.assignment.trainbookingsystem.dto.BookingResponse;
import com.assignment.trainbookingsystem.model.Booking;
import com.assignment.trainbookingsystem.model.SeatAvailability;
import com.assignment.trainbookingsystem.model.User;
import com.assignment.trainbookingsystem.repository.BookingRepository;
import com.assignment.trainbookingsystem.repository.SeatAvailabilityRepository;
import com.assignment.trainbookingsystem.repository.UserRepository;
import com.assignment.trainbookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final SeatAvailabilityRepository seatAvailabilityRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public List<AvailableSeatDto> getAvailableSeats(UUID scheduleId, int startStationOrder, int endStationOrder) {
        // Calculate how many adjacent legs make up this journey
        long requiredLegs = (long) endStationOrder - startStationOrder;

        // Fetch grouped seats that have ALL the required legs available
        List<Object[]> results = seatAvailabilityRepository.findAvailableSeatsForJourney(
                scheduleId, startStationOrder, endStationOrder, requiredLegs);

        // Map the raw SQL Object[] results into our clean DTO
        return results.stream().map(result -> new AvailableSeatDto(
                (String) result[0],  // seatId
                (String) result[1],  // coachType
                (Double) result[2]   // totalSegmentPrice (sum of all leg prices)
        )).collect(Collectors.toList());
    }

    @Override
    @Transactional // CRITICAL: This ensures the pessimistic lock works and rolls back if anything fails
    public BookingResponse bookSeat(BookingRequest request) {

        // 1. Validate the user exists
        User passenger = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        int requiredLegs = request.getEndStationOrder() - request.getStartStationOrder();

        // 2. Fetch AND LOCK the specific legs in the database
        // If another thread is trying to book these exact legs, it will be forced to wait here.
        List<SeatAvailability> legs = seatAvailabilityRepository.findLegsForBookingAndLock(
                request.getScheduleId(),
                request.getSeatId(),
                request.getStartStationOrder(),
                request.getEndStationOrder()
        );

        // 3. Validation Checks
        if (legs.size() != requiredLegs) {
            throw new IllegalStateException("Invalid route segments requested or route mismatch.");
        }

        double totalFare = 0.0;

        for (SeatAvailability leg : legs) {
            if (!"AVAILABLE".equals(leg.getStatus())) {
                // If even one leg of the journey was already booked, roll back the whole transaction
                throw new IllegalStateException("Seat " + request.getSeatId() + " is no longer available for the selected segments.");
            }
            // Dynamically calculate fare based on the segments consumed
            totalFare += leg.getPrice();
            // Mark the leg as booked
            leg.setStatus("BOOKED");
        }

        // 4. Update the locked rows in PostgreSQL
        seatAvailabilityRepository.saveAll(legs);

        // 5. Generate the official Booking receipt
        Booking booking = Booking.builder()
                .passenger(passenger)
                .scheduleId(request.getScheduleId())
                .startStationOrder(request.getStartStationOrder())
                .endStationOrder(request.getEndStationOrder())
                .totalFare(totalFare)
                .bookingTime(LocalDateTime.now())
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        // 6. Return the response to the frontend
        return BookingResponse.builder()
                .bookingId(savedBooking.getId())
                .passengerName(passenger.getUsername())
                .seatId(request.getSeatId())
                .totalFare(totalFare)
                .status("SUCCESS")
                .bookingTime(savedBooking.getBookingTime())
                .build();
    }
}