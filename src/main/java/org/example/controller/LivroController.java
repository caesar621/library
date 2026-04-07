package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.model.Livro;
import org.example.repository.LivroRepository;
import org.example.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public Page<Livro> listarLivros(@RequestParam(name = "disponibilidade", required = false) Boolean disponibilidade, @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {
        if (disponibilidade != null) {
            return service.buscarPorDisponibilidade(disponibilidade, paginacao);
        }
        return service.buscarTodosOsLivros(paginacao);
    }

    @GetMapping("/{livroId}")
    public Livro listarLivroEspecifico(@PathVariable Long livroId) { return service.listarLivroEspecifico(livroId); }

    @GetMapping("/autor/{nome}")
    public Page<Livro> buscarPorAutor(@PathVariable String nome, @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {
        return service.buscarPorAuthor(nome, paginacao);
    }

    @PostMapping
    public Livro criarLivro(@Valid @RequestBody Livro novoLivro) {
        return service.salvarNovoLivro(novoLivro);
    }

    @PutMapping("/{livroId}/devolver")
    public Livro devolverLivro(@PathVariable Long livroId) {
        return service.devolverLivro(livroId);
    }

    @PutMapping("/{livroId}/emprestar/{usuarioId}")
    public Livro emprestarLivro(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        return service.emprestarLivro(livroId, usuarioId);
    }

    @PutMapping("/{livroId}")
    public Livro atualizarLivro(@PathVariable Long livroId, @RequestBody Livro livroAtualizado) { return service.atualizarLivro(livroId, livroAtualizado); }

    @DeleteMapping("/{livroId}")
    public String deletarLivro(@PathVariable Long livroId) { return service.deletarLivro(livroId); }

}
