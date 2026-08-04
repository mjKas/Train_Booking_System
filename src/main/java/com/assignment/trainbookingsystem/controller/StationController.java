package com.assignment.trainbookingsystem.controller;

import com.assignment.trainbookingsystem.model.Station;
import com.assignment.trainbookingsystem.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        return ResponseEntity.ok(stationService.createStation(station));
    }

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        // Now calling the explicitly named method
        return ResponseEntity.ok(stationService.findAllByOrderByStopOrderAsc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(
            @PathVariable Long id,
            @RequestBody Station stationDetails,
            @RequestHeader("X-User-Id") UUID requestingUserId) {
        return ResponseEntity.ok(stationService.updateStation(id, stationDetails, requestingUserId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") UUID requestingUserId) {
        stationService.deleteStation(id, requestingUserId);
        return ResponseEntity.noContent().build();
    }
}