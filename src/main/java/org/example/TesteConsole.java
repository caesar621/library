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

//        System.out.println("Buscando livros que tenham 'Dom' no nome:");

        repository.findByTituloContainingIgnoreCase("Especial").forEach( livro -> {

            String tituloAntigo = livro.getTitulo();
            livro.setTitulo("Senhor dos Anéis: A Sociedade do Anel");
            livro.setDisponibilidade(true);

            repository.save(livro);

            System.out.println("O título de " + tituloAntigo + " agora é " + livro.getTitulo());

        });

//        // 1. Buscamos o livro que queremos mudar (supondo que o ID seja 1)
//// O findById retorna um "Optional", por isso usamos o .orElseThrow()
//        Livro livroNoBanco = repository.findById(4L)
//                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
//
//        System.out.println("Livro antes da mudança: " + livroNoBanco.getTitulo());
//
//// 2. Alteramos os dados do objeto que acabamos de buscar
//        livroNoBanco.setTitulo("Dom Casmurro - Edição Especial");
//        livroNoBanco.setDisponibilidade(false);
//
//// 3. Mandamos o repositório salvar o objeto alterado
//// Como o 'livroNoBanco' já tem o ID 1, o Hibernate fará um UPDATE automaticamente
//        repository.save(livroNoBanco);
//
//        System.out.println("Livro atualizado com sucesso!");

    }

}
