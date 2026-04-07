package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public Page<Usuario> listarTodosUsuarios(@PageableDefault(size = 10, sort = "nome")Pageable paginacao) {
        return service.listarUsuarios(paginacao);
    }

    @PutMapping("/{usuarioId}")
    public Usuario atualizarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuarioNome) {
        return service.atualizarUsuario(usuarioId, usuarioNome);
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario novoUsuario) {
        return service.criarUsuario(novoUsuario);
    }

    @DeleteMapping("/{usuarioId}")
    public String deletarUsuario(@PathVariable Long usuarioId){
        return service.deletarUsuario(usuarioId);
    }

}
