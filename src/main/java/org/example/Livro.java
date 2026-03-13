package org.example;

public class Livro {

    private String titulo = "";
    private String autor = "";
    private Boolean disponibilidade = false;

    public  Livro(String titulo) {
        this.titulo = titulo;
        this.disponibilidade = true;
    }

    @Override
    public String toString() {
        return this.titulo; // Ou "Livro: " + this.titulo
    }

    public String getTitulo() {
        return titulo;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
