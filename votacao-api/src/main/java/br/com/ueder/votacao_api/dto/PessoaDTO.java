package br.com.ueder.votacao_api.dto;

import br.com.ueder.votacao_api.domains.Pessoa;

import java.time.LocalDate;

public record PessoaDTO(
        Long id,
        String nome,
        String matricula,
        CargoDTO cargo,
        SetorDTO setor,
        LocalDate dataAdmissao,
        LocalDate dataRescisao,
        String foto
) {
    public PessoaDTO(Pessoa pessoa) {
        this(   pessoa.getId(), pessoa.getNome(), pessoa.getMatricula(), new CargoDTO(pessoa.getCargo()),
                new SetorDTO(pessoa.getSetor()), pessoa.getDataAdmissao(), pessoa.getDataRescisao(),
                pessoa.getFoto()
        );
    }

    public Pessoa toModel() {
        return new Pessoa(this);
    }
}
