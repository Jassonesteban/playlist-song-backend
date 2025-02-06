package com.music.list.play.controller;

import com.music.list.play.dto.PlayListDTO;
import com.music.list.play.dto.PlayListWithDetailsDTO;
import com.music.list.play.model.PlayList;
import com.music.list.play.service.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class PlayListController {

    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping
    public ResponseEntity<?> crearPlayList(@RequestBody PlayListDTO playListDTO){
        Optional<PlayList> nuevaPlayList = playListService.crearPlayList(playListDTO);

        if(nuevaPlayList.isPresent()){
            String encodedName = URLEncoder.encode(nuevaPlayList.get().getNombre(), StandardCharsets.UTF_8);
            URI location = URI.create("/lists/" + encodedName);
            return ResponseEntity.created(location).body(nuevaPlayList.get());
        } else {
            return ResponseEntity.badRequest().body("Error: no se pudo crear la playlist");
        }
    }

    @PostMapping("/{playlistId}/add-song/{songId}")
    public ResponseEntity<?> agregarCancionAplayList(@PathVariable Long playlistId, @PathVariable Long songId){
        Optional<PlayList> playListOptional = playListService.agregarCancionAplayList(playlistId, songId);

        if(playListOptional.isPresent()){
            return ResponseEntity.ok(playListOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist o cancion no encontrada");
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayListWithDetailsDTO>> obtenerTodasLasPlaylists(){
        return ResponseEntity.ok(playListService.obtenerTodasLasPlaylists());
    }

    @GetMapping("/{listName}")
    public ResponseEntity<PlayListDTO> obtenerPlayListPorNombre(@PathVariable String listName){
        return ResponseEntity.ok(playListService.obtenerPlayListPorNombre(listName));
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> eliminarPlaylist(@PathVariable String listName){
        playListService.eliminarPlaylist(listName);
        return ResponseEntity.noContent().build();
    }

}
