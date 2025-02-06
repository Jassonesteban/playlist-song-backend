package com.music.list.play.service;

import com.music.list.play.dto.SongDTO;
import com.music.list.play.model.Song;
import com.music.list.play.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public SongDTO addSong(SongDTO songDTO){
        Song song = new Song(songDTO.getTitulo(), songDTO.getArtista(), songDTO.getAlbum(), songDTO.getAnno(), songDTO.getGenero());
        songRepository.save(song);
        return new SongDTO(song.getTitulo(), song.getArtista(), song.getAlbum(), song.getAnno(), song.getGenero());
    }

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream()
                .map(song -> new SongDTO(song.getTitulo(), song.getArtista(), song.getAlbum(), song.getAnno(), song.getGenero()))
                .collect(Collectors.toList());
    }

    public SongDTO getSongById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(s -> new SongDTO(s.getTitulo(), s.getArtista(), s.getAlbum(), s.getAnno(), s.getGenero()))
                .orElse(null);
    }

    public SongDTO updateSong(Long id, SongDTO songDTO) {
        Optional<Song> existingSong = songRepository.findById(id);
        if (existingSong.isPresent()) {
            Song song = existingSong.get();
            song.setTitulo(songDTO.getTitulo());
            song.setArtista(songDTO.getArtista());
            song.setAlbum(songDTO.getAlbum());
            song.setAnno(songDTO.getAnno());
            song.setGenero(songDTO.getGenero());
            songRepository.save(song);
            return new SongDTO(song.getTitulo(), song.getArtista(), song.getAlbum(), song.getAnno(), song.getGenero());
        }
        return null;
    }

    public boolean deleteSong(Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
