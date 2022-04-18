package com.sevi.cadegouline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevi.cadegouline.entities.Track;
import com.sevi.cadegouline.services.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/track")
public class TrackController {
    @Value("${spring.config.additional-location}")
    private String fileDir;
    private final TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping(value="/insert")
    public ResponseEntity<String> insert(@RequestParam(value = "fileTrack") MultipartFile fileTrack, @RequestParam(value="track") String jsonTrack) throws IOException {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String nowString = formatter.format(now);

        System.out.println( nowString + " POST Request to add a track : " + fileTrack.getOriginalFilename() + " " + jsonTrack);
        Track track = new ObjectMapper().readValue(jsonTrack, Track.class);

        System.out.println("File will be saved at : " + this.fileDir);
        byte[] bytes = fileTrack.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(this.fileDir + track.getPathToFile());
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        String command = "ffmpeg -i \"" + this.fileDir + track.getPathToFile() +
                "\" -vn -acodec libvorbis \"" +
                this.fileDir + track.getPathToFile().substring(0, track.getPathToFile().length()-3) +
                "ogg\"";

        Runtime process = Runtime.getRuntime();
        process.exec(command);

        if (trackService.addTrack(track) != null) {
            return ResponseEntity.ok().body("OK");
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/getAll")
    public List<Track> getAll() {
        return trackService.getAll();
    }
}
