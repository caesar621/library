package org.example;

public class Main {
    public static void main(String[] args) {

    Biblioteca minhaBiblioteca = new Biblioteca();

    Livro l1 = new Livro("O Senho dos Anéis");
    Livro l2 = new Livro("Java para Iniciantes");
    Livro l3 = new Livro("O Rei de Amarelo");

    minhaBiblioteca.cadastrarLivro(l1);
    minhaBiblioteca.cadastrarLivro(l2);

    Usuario u1 = new Usuario("Millie");

    minhaBiblioteca.realizarEmprestimo(u1, "Java para Iniciantes");

    minhaBiblioteca.listarAcervo();


    }
}
