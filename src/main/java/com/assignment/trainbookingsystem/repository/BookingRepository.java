package com.assignment.trainbookingsystem.repository;
import com.assignment.trainbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}