package com.assignment.trainbookingsystem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class AvailableSeatDto {
    private String seatId;
    private String coachType;
    private double totalSegmentPrice;
}
