package com.music.list.play.dto;

import java.util.List;

public class PlayListDTO {
    private String nombre;
    private String descripcion;
    private List<Long> canciones;

    public PlayListDTO(String nombre, String descripcion, List<Long> canciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
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

    public List<Long> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Long> canciones) {
        this.canciones = canciones;
    }
}
