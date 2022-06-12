package com.sevi.cadegouline.services;

import com.sevi.cadegouline.entities.Program;
import com.sevi.cadegouline.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program addProgram(Program program) {
        return programRepository.save(program);
    }
}
