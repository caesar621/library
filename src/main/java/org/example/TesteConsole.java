package org.example;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class TesteConsole implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {


        Usuario u1 = usuarioRepository.findById(1L).orElse(null);

        if (u1 != null) {

            System.out.println("Usuários: " + u1.getNome());
            System.out.println("Qtd de livros: " + u1.getLivros().size());
            u1.getLivros().forEach(item -> System.out.println(" - " + item.getTitulo()));
        }

    }
}
