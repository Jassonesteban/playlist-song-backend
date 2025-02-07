package com.music.list.play;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de playlists de musica", version = "1.0", description = "API para gestionar playlists y canciones"))
public class PlayApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlayApplication.class, args);
	}

}
