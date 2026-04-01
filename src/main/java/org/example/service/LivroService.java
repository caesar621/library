package org.example.service;

import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.LivroRepository;
import org.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Livro> buscarTodosOsLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorAuthor(String nome) {
        return livroRepository.findByAutorContainingIgnoreCase(nome);
    }

    public void devolverLivro(Long livroId) {
        Livro livroDevolvido = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livroDevolvido.getDisponibilidade()) {
            livroDevolvido.setUsuario(null);
            livroDevolvido.setDisponibilidade(true);
            livroRepository.save(livroDevolvido);
        }


    }

    public void emprestarLivro(Long livroId, Long usuarioId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        if (!livro.getDisponibilidade()) {
            throw new RuntimeException("Este livro já está emprestado");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        livro.setUsuario(usuario);
        livro.setDisponibilidade(false);
        livroRepository.save(livro);

    }

}
