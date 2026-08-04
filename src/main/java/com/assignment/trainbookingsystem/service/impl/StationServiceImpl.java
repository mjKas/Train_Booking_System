package com.assignment.trainbookingsystem.service.impl;

import com.assignment.trainbookingsystem.model.Station;
import com.assignment.trainbookingsystem.repository.StationRepository;
import com.assignment.trainbookingsystem.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }
    @Override
    public Station updateStation(Long id, Station stationDetails, UUID requestingUserId) {
        //verifyAdminRole(requestingUserId);

        Station existingStation = stationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Station not found with ID: " + id));

        existingStation.setName(stationDetails.getName());
        existingStation.setStopOrder(stationDetails.getStopOrder());

        return stationRepository.save(existingStation);
    }

    @Override
    public void deleteStation(Long id, UUID requestingUserId) {
       // verifyAdminRole(requestingUserId);

        if (!stationRepository.existsById(id)) {
            throw new IllegalArgumentException("Station not found with ID: " + id);
        }
        stationRepository.deleteById(id);
    }
    @Override
    public List<Station> getAllStations() {
        // We use the custom repository method we defined earlier to ensure
        // the stations are always returned in the correct geographical order (1, 2, 3...)
        return stationRepository.findAllByOrderByStopOrderAsc();
    }
    @Override
    public List<Station> findAllByOrderByStopOrderAsc() {
        return stationRepository.findAllByOrderByStopOrderAsc();
    }
}