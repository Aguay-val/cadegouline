package com.sevi.cadegouline.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sevi.cadegouline.entities.Program;
import com.sevi.cadegouline.services.ProgramService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/program")
public class ProgramController {
    @Value("${spring.config.additional-location}")
    private String fileDir;

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<String> insert(@RequestParam(value = "fileProgram") MultipartFile fileProgram, @RequestParam(value = "program") String jsonProgram) throws IOException {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        String nowString = formatter.format(now);

        System.out.println( nowString + " POST Request to add a program : " + fileProgram.getOriginalFilename() + " " + jsonProgram);
        Boolean goodExtension = fileProgram.getOriginalFilename().substring(fileProgram.getOriginalFilename().length() -4).equalsIgnoreCase(".mp3");
        if (!goodExtension) {
            now = new Date();
            nowString = formatter.format(now);
            System.out.println(nowString + " ERROR : file '" + fileProgram.getOriginalFilename() + "' have not a correct extension");
            return new ResponseEntity<>("Le fichier n'a pas une extension correcte", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonProgram);
            Program program = new Program();
            program.setTitle((String)jsonObject.get("title"));
            program.setDateProgram(Instant.parse((String)jsonObject.get("date_program")));
            program.setPathToFile((String) jsonObject.get("path_to_file"));

            System.out.println("File will be saved at : " + this.fileDir);

            try {
                byte[] bytes = fileProgram.getBytes();
                FileOutputStream fileOutputStream = new FileOutputStream(this.fileDir + program.getPathToFile());
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            } catch (Exception e) {
                return new ResponseEntity<>("Problème lors de la copie du fichier MP3", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }

            if (programService.addProgram(program) != null) {
                return new ResponseEntity<>("OK", new HttpHeaders(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}