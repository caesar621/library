package org.example.controller;

import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return service.listarUsuarios();
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
