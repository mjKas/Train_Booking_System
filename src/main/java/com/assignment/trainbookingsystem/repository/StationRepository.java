package com.assignment.trainbookingsystem.repository;
import com.assignment.trainbookingsystem.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findAllByOrderByStopOrderAsc();
}