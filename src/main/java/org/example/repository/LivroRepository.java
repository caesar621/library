package org.example.repository;

import org.example.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    Page<Livro> findByAutorContainingIgnoreCase(String autor, Pageable paginacao);

    Page<Livro> findByDisponibilidade(Boolean disponibilidade, Pageable paginacao);

}
