package com.sevi.cadegouline.services;

import com.sevi.cadegouline.entities.Track;
import com.sevi.cadegouline.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    @Value("${spring.config.additional-location}")
    private String fileDir;

    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track addTrack(Track track) {
        return trackRepository.save(track);
    }

    public List<Track> getAll() {
        return trackRepository.findAll();
    }

}
