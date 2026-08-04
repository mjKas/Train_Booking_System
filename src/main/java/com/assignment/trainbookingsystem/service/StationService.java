package com.assignment.trainbookingsystem.service;
import com.assignment.trainbookingsystem.model.Station;
import java.util.List;
import java.util.UUID;

public interface StationService {
    Station createStation(Station station);
    List<Station> getAllStations();
    Station updateStation(Long id, Station stationDetails, UUID requestingUserId);
    void deleteStation(Long id, UUID requestingUserId);
    List<Station> findAllByOrderByStopOrderAsc();
}