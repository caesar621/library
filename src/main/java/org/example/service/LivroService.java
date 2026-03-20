package org.example.service;

import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> buscarTodosOsLivros() {
        return repository.findAll();
    }

    public void emprestarLivro(Long livroId, Usuario usuario) {
        Livro livro = repository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        if (!livro.getDisponibilidade()) {
            throw new RuntimeException("Este livro já está emprestado");
        }

        livro.setUsuario(usuario);
        livro.setDisponibilidade(false);
        repository.save(livro);

    }

}
