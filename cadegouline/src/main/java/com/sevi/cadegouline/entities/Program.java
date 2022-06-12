package com.sevi.cadegouline.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "program")
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Lob
    @Column(name = "path_to_file", nullable = false)
    private String pathToFile;

    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date_program", nullable = false)
    @JsonProperty(value="date_program")
    private Instant dateProgram;

    public Instant getDateProgram() {
        return dateProgram;
    }

    public void setDateProgram(Instant dateProgram) {
        this.dateProgram = dateProgram;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}