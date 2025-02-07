package com.music.list.play.dto;

import java.util.List;

public class PlayListWithDetailsDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<SongDTO> canciones;

    public PlayListWithDetailsDTO(Long id, String nombre, String descripcion, List<SongDTO> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.canciones = canciones;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SongDTO> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<SongDTO> canciones) {
        this.canciones = canciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
