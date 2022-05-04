package com.sevi.cadegouline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevi.cadegouline.entities.Track;
import com.sevi.cadegouline.services.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        String nowString = formatter.format(now);

        System.out.println( nowString + " POST Request to add a track : " + fileTrack.getOriginalFilename() + " " + jsonTrack);
        Boolean goodExtension = fileTrack.getOriginalFilename().substring(fileTrack.getOriginalFilename().length() -4).equalsIgnoreCase(".mp3");
        if (!goodExtension) {
            now = new Date();
            nowString = formatter.format(now);
            System.out.println(nowString + " ERROR : file '" + fileTrack.getOriginalFilename() + "' have not a correct extension");
            return new ResponseEntity<>("Le fichier n'a pas une extension correcte", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Track track = new ObjectMapper().readValue(jsonTrack, Track.class);

        System.out.println("File will be saved at : " + this.fileDir);
        byte[] bytes = fileTrack.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(this.fileDir + track.getPathToFile());
        fileOutputStream.write(bytes);
        fileOutputStream.close();

/*
        String command = "ffmpeg -i \"" + this.fileDir + track.getPathToFile() +
                "\" -vn -acodec libvorbis \"" +
                this.fileDir + track.getPathToFile().substring(0, track.getPathToFile().length()-3) +
                "ogg\"";
        System.out.println(command);

        Runtime process = Runtime.getRuntime();
        process.exec(command);
*/
        if (trackService.addTrack(track) != null) {
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<>("OK", new HttpHeaders(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/getAll")
    public List<Track> getAll() {
        return trackService.getAll();
    }
}
