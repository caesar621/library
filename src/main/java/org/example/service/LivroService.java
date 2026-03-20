package org.example.service;

import org.example.model.Livro;
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

}
