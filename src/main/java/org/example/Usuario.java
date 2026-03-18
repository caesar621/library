package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    private String nome;
    private List<Livro> livrosEmPosse;

    public Usuario(String nome) {
        this.nome = nome;
        this.livrosEmPosse = new ArrayList<>();
    }

    // # Métodos
    public void adicionarLivro(Livro livro) {
        if (!livro.getDisponibilidade()) {
            System.out.println("Livro indisponível");
        } else if (livrosEmPosse.size() < 3) {
            livrosEmPosse.add(livro);
            livro.setDisponibilidade(false);
            System.out.println( nome + " pegou o livro " + livro + " emprestado" );
        } else {
                System.out.println("Limite de livros atingido");
            }
        }

    public void devolverLivro(Livro livro) {
        if (livrosEmPosse.contains(livro)) {
            livrosEmPosse.remove(livro);
            livro.setDisponibilidade(true);
            System.out.println(nome + " devolveu " + livro.getTitulo());
        } else {
            System.out.println("Este livro não está com o usuário " + nome);
        }
    }

    // #Get e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivrosEmPosse() {
        return livrosEmPosse;
    }

    public void setLivrosEmPosse(List<Livro> livrosEmPosse) {
        this.livrosEmPosse = livrosEmPosse;
    }


}
