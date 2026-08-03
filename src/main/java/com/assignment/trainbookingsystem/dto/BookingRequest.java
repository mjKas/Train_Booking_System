package com.assignment.trainbookingsystem.dto;
import lombok.Data;
import java.util.UUID;
public class BookingRequest {
    private UUID userId;
    private UUID scheduleId;
    private String seatId;
    private int startStationOrder;
    private int endStationOrder;
}
