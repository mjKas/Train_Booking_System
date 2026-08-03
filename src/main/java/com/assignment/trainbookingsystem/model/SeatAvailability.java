package com.assignment.trainbookingsystem.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "seat_availability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "schedule_id", nullable = false)
    private UUID scheduleId;

    @Column(name = "seat_id", nullable = false)
    private String seatId;

    @Column(name = "coach_type")
    private String coachType; // "RESERVED" or "UNRESERVED"

    @Column(name = "start_station_order", nullable = false)
    private int startStationOrder;

    @Column(name = "end_station_order", nullable = false)
    private int endStationOrder;

    @Column(name = "status", nullable = false)
    private String status; // "AVAILABLE", "LOCKED", "BOOKED"

    @Column(name = "price")
    private double price;

    // For pessimistic locking/concurrency control
    @Version
    private Integer version;
}
