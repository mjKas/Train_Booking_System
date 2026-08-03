package com.assignment.trainbookingsystem.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User passenger;

    @Column(name = "schedule_id", nullable = false)
    private UUID scheduleId;

    @Column(name = "start_station_order", nullable = false)
    private int startStationOrder;

    @Column(name = "end_station_order", nullable = false)
    private int endStationOrder;

    @Column(name = "total_fare", nullable = false)
    private double totalFare;

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;
}
