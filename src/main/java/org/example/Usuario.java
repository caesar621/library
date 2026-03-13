package org.example;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private List<Livro> livrosEmPosse;

    public Usuario(String nome) {
        this.nome = nome;
        this.livrosEmPosse = new ArrayList<>();
    }

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

    public String getNome() {
        return nome;
    }

    public List<Livro> getLivrosEmPosse() {
        return livrosEmPosse;
    }

}
