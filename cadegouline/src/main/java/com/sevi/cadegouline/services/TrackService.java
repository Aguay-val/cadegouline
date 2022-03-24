package com.sevi.cadegouline.services;

import com.sevi.cadegouline.entities.Track;
import com.sevi.cadegouline.repositories.TrackRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track addTrack(Track track) {
        return trackRepository.save(track);
    }

}
