package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}