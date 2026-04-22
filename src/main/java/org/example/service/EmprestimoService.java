package org.example.service;

import org.example.dto.EmprestimoResponseDTO;
import org.example.model.Emprestimo;
import org.example.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoResponseDTO buscarPorId(Long EmprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(EmprestimoId).orElseThrow();

        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getUsuario().getNome(),
                emprestimo.getLivro().getTitulo(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getStatus()
        );

    }

}
