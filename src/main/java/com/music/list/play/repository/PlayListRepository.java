package com.music.list.play.repository;

import com.music.list.play.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    Optional<PlayList> findByNombre(String nombre);

}
