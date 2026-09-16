package br.edu.ifrn.lab4.dto;

import java.time.LocalDate;

public record TaskRequestDTO(
        String titulo,
        String descricao,
        LocalDate prazo
) {
}