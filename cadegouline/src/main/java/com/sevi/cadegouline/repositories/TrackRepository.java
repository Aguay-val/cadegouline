package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}