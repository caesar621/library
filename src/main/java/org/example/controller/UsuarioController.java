package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarTodosUsuarios(@PageableDefault(size = 10, sort = "nome")Pageable paginacao) {
        return ResponseEntity.ok(service.listarUsuarios(paginacao));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuarioNome) {
        return ResponseEntity.ok(service.atualizarUsuario(usuarioId, usuarioNome));
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario novoUsuario) {
        return ResponseEntity.ok(service.criarUsuario(novoUsuario));
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long usuarioId){
        return ResponseEntity.ok(service.deletarUsuario(usuarioId));
    }

}
