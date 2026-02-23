package br.com.ueder.votacao_api.dto;

import br.com.ueder.votacao_api.domains.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CargoDTO(
        Long id,
        @NotBlank
        @Size(min = 3, max = 80)
        String descricao
) {
    public CargoDTO(Cargo cargo) {
        this(cargo.getId(), cargo.getDescricao());
    }

    public Cargo toModel(){
        return new Cargo(this);
    }
}
