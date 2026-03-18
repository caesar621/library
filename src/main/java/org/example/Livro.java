package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String titulo = "";
    private String autor = "";
    private Boolean disponibilidade = false;

    public Livro() {}

    public  Livro(String titulo) {
        this.titulo = titulo;
        this.disponibilidade = true;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
