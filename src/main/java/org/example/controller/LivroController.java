package org.example.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.model.Livro;
import org.example.repository.LivroRepository;
import org.example.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<Page<Livro>> listarLivros(@RequestParam(name = "disponibilidade", required = false) Boolean disponibilidade, @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {
        if (disponibilidade != null) {
            return ResponseEntity.ok(service.buscarPorDisponibilidade(disponibilidade, paginacao));
        }
        return ResponseEntity.ok(service.buscarTodosOsLivros(paginacao));
    }

    @GetMapping("/{livroId}")
    public ResponseEntity<Livro> listarLivroEspecifico(@PathVariable Long livroId) { return ResponseEntity.ok(service.listarLivroEspecifico(livroId)); }

    @GetMapping("/autor/{nome}")
    public ResponseEntity<Page<Livro>> buscarPorAutor(@PathVariable String nome, @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {
        return ResponseEntity.ok(service.buscarPorAuthor(nome, paginacao));
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro novoLivro) {
        return ResponseEntity.ok(service.salvarNovoLivro(novoLivro));
    }

    @PutMapping("/{livroId}/devolver")
    public ResponseEntity<Livro> devolverLivro(@PathVariable Long livroId) {
        return ResponseEntity.ok(service.devolverLivro(livroId));
    }

    @PutMapping("/{livroId}/emprestar/{usuarioId}")
    public ResponseEntity<Livro> emprestarLivro(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.emprestarLivro(livroId, usuarioId));
    }

    @PutMapping("/{livroId}")
    public ResponseEntity <Livro> atualizarLivro(@PathVariable Long livroId, @RequestBody Livro livroAtualizado) { return ResponseEntity.ok(service.atualizarLivro(livroId, livroAtualizado)); }

    @DeleteMapping("/{livroId}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long livroId) { return ResponseEntity.ok(service.deletarLivro(livroId)); }

}
