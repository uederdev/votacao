package br.com.ueder.votacao_api.dto;

import java.time.LocalDate;

public record AtualizaDadosPessoaDTO(
        Long id,
        String nome,
        Long cargo,
        Long setor,
        LocalDate dataRescisao,
        String foto
) {
}
