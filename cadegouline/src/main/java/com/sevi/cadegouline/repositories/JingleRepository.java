package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Jingle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JingleRepository extends JpaRepository<Jingle, Long> {
}