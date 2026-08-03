package com.assignment.trainbookingsystem.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class BookingResponse {
    private UUID bookingId;
    private String passengerName;
    private String seatId;
    private double totalFare;
    private String status;
    private LocalDateTime bookingTime;
}
