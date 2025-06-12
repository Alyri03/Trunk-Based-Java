package com.trunk.based.model;

public class Task {
    private Long id;
    private String titulo;
    private boolean completado;

    public Task() {
    }

    public Task(Long id, String titulo, boolean completado) {
        this.id = id;
        this.titulo = titulo;
        this.completado = completado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
