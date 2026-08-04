package com.assignment.trainbookingsystem.service.impl;
import com.assignment.trainbookingsystem.model.TrainSchedule;
import com.assignment.trainbookingsystem.repository.TrainScheduleRepository;
import com.assignment.trainbookingsystem.service.TrainScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private final TrainScheduleRepository trainScheduleRepository;

    @Override
    public TrainSchedule createSchedule(TrainSchedule schedule) {
        return trainScheduleRepository.save(schedule);
    }

    @Override
    public List<TrainSchedule> getAllSchedules() {
        return trainScheduleRepository.findAll();
    }

    @Override
    public TrainSchedule getScheduleById(UUID id) {
        return trainScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Train schedule not found with ID: " + id));
    }

    @Override
    public TrainSchedule updateSchedule(UUID id, TrainSchedule scheduleDetails) {
        TrainSchedule existingSchedule = getScheduleById(id);

        existingSchedule.setTrainName(scheduleDetails.getTrainName());
        existingSchedule.setDepartureTime(scheduleDetails.getDepartureTime());

        return trainScheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteSchedule(UUID id) {
        TrainSchedule existingSchedule = getScheduleById(id);

        // Soft delete: Just flip the flag and save it back
        existingSchedule.setActive(false);
        trainScheduleRepository.save(existingSchedule);
    }

    @Override
    public List<TrainSchedule> findAllByIsActiveTrue() {
        return trainScheduleRepository.findAllByIsActiveTrue();
    }


}