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
    public List<Livro> listarLivros(@RequestParam(name = "disponibilidade", required = false) Boolean disponibilidade) {
        if (disponibilidade != null) {
            return service.buscarPorDisponibilidade(disponibilidade);
        }
        return service.buscarTodosOsLivros();
    }

    @GetMapping("/autor/{nome}")
    public List<Livro> buscarPorAutor(@PathVariable String nome) {
        return service.buscarPorAuthor(nome);
    }

    @PutMapping("/{livroId}/devolver")
    public Livro devolverLivro(@PathVariable Long livroId) {
        return service.devolverLivro(livroId);
    }

    @PutMapping("/{livroId}/emprestar/{usuarioId}")
    public Livro emprestarLivro(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        return service.emprestarLivro(livroId, usuarioId);
    }

    @PostMapping
    public Livro criarLivro(@RequestBody Livro novoLivro) {
        return service.salvarNovoLivro(novoLivro);
    }
}
