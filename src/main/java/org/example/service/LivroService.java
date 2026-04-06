package org.example.service;

import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.LivroRepository;
import org.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Livro> buscarTodosOsLivros(Pageable paginacao) {
        return livroRepository.findAll(paginacao);
    }

    public Livro listarLivroEspecifico(Long livroId) {

        return livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Page<Livro> buscarPorDisponibilidade(Boolean disponibilidade, Pageable paginacao) {
        return livroRepository.findByDisponibilidade(disponibilidade, paginacao);
    }

    public List<Livro> buscarPorAuthor(String nome) {
        return livroRepository.findByAutorContainingIgnoreCase(nome);
    }


    public Livro salvarNovoLivro(Livro livro) {
        livro.setDisponibilidade(true);
        livro.setUsuario(null);

        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long livroId, Livro livroAtualizado) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livroAtualizado.getTitulo() != null) {
            livro.setTitulo(livroAtualizado.getTitulo());
        }

        if (livroAtualizado.getAutor() != null) {
            livro.setAutor(livroAtualizado.getAutor());
        }

        return livroRepository.save(livro);
    }

    public Livro devolverLivro(Long livroId) {
        Livro livroDevolvido = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livroDevolvido.getDisponibilidade()) {
            livroDevolvido.setUsuario(null);
            livroDevolvido.setDisponibilidade(true);
            return livroRepository.save(livroDevolvido);
        } else {
            throw new RuntimeException("Este livro já se encontra disponível");
        }

    }

    public Livro emprestarLivro(Long livroId, Long usuarioId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        if (!livro.getDisponibilidade()) {
            throw new RuntimeException("Este livro já está emprestado");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        livro.setUsuario(usuario);
        livro.setDisponibilidade(false);
        return livroRepository.save(livro);

    }

    public String deletarLivro(Long livroId) {

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livroRepository.deleteById(livroId);

        return "Livro deletado com sucesso";
    }

}
