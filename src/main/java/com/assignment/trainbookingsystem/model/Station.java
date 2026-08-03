package com.assignment.trainbookingsystem.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name; // e.g., "Colombo Fort", "Kandy", "Nanu Oya", "Badulla"

    @Column(name = "stop_order", nullable = false, unique = true)
    private int stopOrder; // e.g., 1, 2, 3, 4
}