package org.example.controller;

import org.example.model.Livro;
import org.example.repository.LivroRepository;
import org.example.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<Livro> listarTodos() {
        return service.buscarTodosOsLivros();
    }

    @GetMapping("/autor/{nome}")
    public List<Livro> buscarPorAutor(@PathVariable String nome) {
        return service.buscarPorAuthor(nome);
    }

    @PutMapping("/{livroId}")
    public void devolverLivro(@PathVariable Long livroId) {
        service.devolverLivro(livroId);
    }

    @PutMapping("/{livroId}/emprestar/{usuarioId}")
    public void emprestarLivro(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        service.emprestarLivro(livroId, usuarioId);
    }
}
