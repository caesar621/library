package org.example.dto;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        String nomeUsuario,
        String tituloLivro,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        String status
) {

}
