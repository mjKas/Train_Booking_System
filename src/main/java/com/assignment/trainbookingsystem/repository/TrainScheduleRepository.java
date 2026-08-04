package com.assignment.trainbookingsystem.repository;
import com.assignment.trainbookingsystem.model.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, UUID> {
    List<TrainSchedule> findAllByIsActiveTrue();
}