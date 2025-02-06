package com.music.list.play.service;

import com.music.list.play.dto.PlayListDTO;
import com.music.list.play.dto.PlayListWithDetailsDTO;
import com.music.list.play.dto.SongDTO;
import com.music.list.play.model.PlayList;
import com.music.list.play.model.Song;
import com.music.list.play.repository.PlayListRepository;
import com.music.list.play.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    private final PlayListRepository playListRepository;
    private final SongRepository songRepository;


    public PlayListService(PlayListRepository playListRepository, SongRepository songRepository) {
        this.playListRepository = playListRepository;
        this.songRepository = songRepository;
    }

    public Optional<PlayList> crearPlayList(PlayListDTO playListDTO){
        if(playListDTO.getNombre() == null || playListDTO.getNombre().isEmpty()){
            return Optional.empty();
        }

        List<Song> canciones = playListDTO.getCanciones().stream()
                .map(songRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        PlayList nuevaPlaylist = new PlayList();
        nuevaPlaylist.setNombre(playListDTO.getNombre());
        nuevaPlaylist.setDescripcion(playListDTO.getDescripcion());
        nuevaPlaylist.setCanciones(canciones);

        playListRepository.save(nuevaPlaylist);
        return Optional.of(nuevaPlaylist);
    }

    public List<PlayListWithDetailsDTO> obtenerTodasLasPlaylists(){
        List<PlayList> playLists = playListRepository.findAll();

        return playLists.stream().map(playList -> {
            List<SongDTO> songDetails = playList.getCanciones().stream()
                    .map(song -> new SongDTO(song.getTitulo(), song.getArtista(), song.getAlbum(), song.getAnno(), song.getGenero()))
                    .collect(Collectors.toList());

            return new PlayListWithDetailsDTO(playList.getNombre(), playList.getDescripcion(), songDetails);
        }).collect(Collectors.toList());

    }

    public PlayListDTO obtenerPlayListPorNombre(String nombre){
        return playListRepository.findByNombre(nombre)
                .map(this::convertToDTO)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lISTA NO ENCONTRADA"));

    }

    public void eliminarPlaylist(String nombre){
        PlayList playList = playListRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no encontrada"));
                playListRepository.delete(playList);
    }

    private PlayListDTO convertToDTO(PlayList playList){
        List<Long> songIds = playList.getCanciones().stream().map(Song::getId).collect(Collectors.toList());
        return new PlayListDTO(playList.getNombre(), playList.getDescripcion(), songIds);
    }

    public Optional<PlayList> agregarCancionAplayList(Long playlistId, Long songId){
        Optional<PlayList> playListOptional = playListRepository.findById(playlistId);
        Optional<Song> songOptional = songRepository.findById(songId);

        if(playListOptional.isPresent() && songOptional.isPresent()){
            PlayList playList = playListOptional.get();
            Song song = songOptional.get();
            playList.getCanciones().add(song);
            playListRepository.save(playList);
            return Optional.of(playList);
        }

        return Optional.empty();
    }

    private SongDTO convertSongToDTO(Song song){
        return new SongDTO(song.getTitulo(), song.getArtista(), song.getAlbum(),song.getAnno(), song.getGenero());
    }

    private Song convertToEntity(SongDTO songDTO){
        Song song = new Song();
        song.setTitulo(songDTO.getTitulo());
        song.setArtista(songDTO.getArtista());
        song.setAlbum(songDTO.getAlbum());
        song.setAnno(songDTO.getAnno());
        song.setGenero(songDTO.getGenero());

        return song;
    }

}
