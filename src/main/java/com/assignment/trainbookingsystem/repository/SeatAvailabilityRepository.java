package com.assignment.trainbookingsystem.repository;
import com.assignment.trainbookingsystem.model.SeatAvailability;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, UUID> {

    @Query("SELECT s.seatId, s.coachType, SUM(s.price) " +
            "FROM SeatAvailability s " +
            "WHERE s.scheduleId = :scheduleId " +
            "AND s.startStationOrder >= :startOrder " +
            "AND s.startStationOrder < :endOrder " +
            "AND s.status = 'AVAILABLE' " +
            "GROUP BY s.seatId, s.coachType " +
            "HAVING COUNT(s.id) = :requiredLegs")
    List<Object[]> findAvailableSeatsForJourney(@Param("scheduleId") UUID scheduleId,
                                                @Param("startOrder") int startOrder,
                                                @Param("endOrder") int endOrder,
                                                @Param("requiredLegs") long requiredLegs);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SeatAvailability s " +
            "WHERE s.scheduleId = :scheduleId " +
            "AND s.seatId = :seatId " +
            "AND s.startStationOrder >= :startOrder " +
            "AND s.startStationOrder < :endOrder")
    List<SeatAvailability> findLegsForBookingAndLock(@Param("scheduleId") UUID scheduleId,
                                                     @Param("seatId") String seatId,
                                                     @Param("startOrder") int startOrder,
                                                     @Param("endOrder") int endOrder);
}