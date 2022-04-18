package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query(value = "SELECT * FROM program order by id desc LIMIT :number, 1;", nativeQuery = true)
    Program findProgramOfTheDay(@Param("number") Integer number);
}