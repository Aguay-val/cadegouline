package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Jingle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JingleRepository extends JpaRepository<Jingle, Long> {
    @Query("SELECT j.id FROM Jingle j")
    List<Long> findAllIds();
}