package com.assignment.trainbookingsystem.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "train_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "train_name", nullable = false)
    private String trainName;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
}