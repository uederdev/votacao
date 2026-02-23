package br.com.ueder.votacao_api.dto;

import br.com.ueder.votacao_api.domains.Cargo;
import br.com.ueder.votacao_api.domains.Pessoa;
import br.com.ueder.votacao_api.services.CargoService;

import java.time.LocalDate;

public record DadosPessoaDTO(
        Long id,
        String nome,
        String matricula,
        Long cargo,
        Long setor,
        LocalDate dataAdmissao,
        LocalDate dataRescisao,
        String foto) {

    public Pessoa toModel() {
        return new Pessoa(this);
    }

    public DadosPessoaDTO(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getMatricula(), pessoa.getCargo().getId(),
                pessoa.getSetor().getId(), pessoa.getDataAdmissao(), pessoa.getDataRescisao(), pessoa.getFoto());
    }

    public PessoaDTO toPessoaDTO() {
        return new PessoaDTO(
                this.id(), this.nome(), this.matricula(), new CargoDTO(this.cargo(), ""),
                new SetorDTO(this.setor(), ""),  this.dataAdmissao(), this.dataRescisao(),
                this.foto()
        );
    }
}
