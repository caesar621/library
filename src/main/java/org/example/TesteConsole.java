package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TesteConsole implements CommandLineRunner {

    @Autowired
    private LivroRepository repository;

    @Override
    public void run(String... args ) throws Exception {

//        System.out.println("--- Cadastrando novo livro com Spring Boot ---");
//
//        Livro l1 = new Livro("Dom Casmurro");
//        repository.save(l1);
//
//        System.out.println("Livro salvo. Listando todos do banco:");
//        repository.findAll().forEach(System.out::println);

        System.out.println("Buscando livros que tenham 'Dom' no nome:");
        repository.findByTituloContainingIgnoreCase("Dom").forEach(System.out::println);
    }

}
