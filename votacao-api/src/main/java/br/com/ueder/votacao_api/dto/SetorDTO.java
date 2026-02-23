package br.com.ueder.votacao_api.dto;

import br.com.ueder.votacao_api.domains.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetorDTO(
        Long id,
        @NotBlank
        @Size(min = 3, max = 80)
        String descricao
) {
        public SetorDTO(Setor x) {
                this(x.getId(), x.getDescricao());
        }

        public Setor toModel(){
                return new Setor(this);
        }
}
