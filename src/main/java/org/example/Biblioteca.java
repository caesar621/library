package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Biblioteca {

    @Id
    private List<Livro> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public void cadastrarLivro(Livro Livro) {
        acervo.add(Livro);
        System.out.println(Livro.getTitulo() + " foi adicionado ao acervo");
    }

    public Livro buscarLivroPorTitulo(String tituloProcurado) {
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(tituloProcurado)) {
                System.out.println("Livro encontrado: " + livro);
                return livro;
            }
        }
        System.out.println("Livro " + tituloProcurado + " não encontrado no acervo.");
        return null;
    }

    public void realizarEmprestimo(Usuario usuario, String tituloProcurado) {
        Livro livroEncontrado = buscarLivroPorTitulo(tituloProcurado);

        if (livroEncontrado != null) {
            usuario.adicionarLivro(livroEncontrado);
        } else {
            System.out.println("Erro: O livro '" + tituloProcurado + "' não existe no acervo.");
        }
    }

    public void listarAcervo() {
        for (Livro livro : acervo) {
            System.out.println(livro);
        }
    }

}
