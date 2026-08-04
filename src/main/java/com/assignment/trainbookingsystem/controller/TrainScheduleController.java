package com.assignment.trainbookingsystem.controller;

import com.assignment.trainbookingsystem.model.TrainSchedule;
import com.assignment.trainbookingsystem.service.TrainScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class TrainScheduleController {

    private final TrainScheduleService trainScheduleService;

    @PostMapping
    public ResponseEntity<TrainSchedule> createSchedule(@RequestBody TrainSchedule schedule) {
        return ResponseEntity.ok(trainScheduleService.createSchedule(schedule));
    }

    @GetMapping
    public ResponseEntity<List<TrainSchedule>> getActiveSchedules() {
        return ResponseEntity.ok(trainScheduleService.findAllByIsActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainSchedule> getScheduleById(@PathVariable UUID id) {
        return ResponseEntity.ok(trainScheduleService.getScheduleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainSchedule> updateSchedule(
            @PathVariable UUID id,
            @RequestBody TrainSchedule scheduleDetails) {
        return ResponseEntity.ok(trainScheduleService.updateSchedule(id, scheduleDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID id) {
        trainScheduleService.deleteSchedule(id);
        // Returns a 204 No Content status code, which is standard for successful deletions
        return ResponseEntity.noContent().build();
    }
}