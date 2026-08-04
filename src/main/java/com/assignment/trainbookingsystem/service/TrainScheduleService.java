package com.assignment.trainbookingsystem.service;

import com.assignment.trainbookingsystem.model.TrainSchedule;
import java.util.List;
import java.util.UUID;

public interface TrainScheduleService {
    TrainSchedule createSchedule(TrainSchedule schedule);
    List<TrainSchedule> getAllSchedules();
    TrainSchedule getScheduleById(UUID id);
    TrainSchedule updateSchedule(UUID id, TrainSchedule scheduleDetails);
    void deleteSchedule(UUID id);
}