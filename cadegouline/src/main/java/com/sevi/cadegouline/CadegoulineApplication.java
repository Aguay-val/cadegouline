package com.sevi.cadegouline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CadegoulineApplication {
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec("sudo apt-get install ffmpeg");
		SpringApplication.run(CadegoulineApplication.class, args);
	}
}
