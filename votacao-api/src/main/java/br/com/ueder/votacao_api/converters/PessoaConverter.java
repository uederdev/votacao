package br.com.ueder.votacao_api.converters;

import br.com.ueder.votacao_api.domains.Cargo;
import br.com.ueder.votacao_api.domains.Pessoa;
import br.com.ueder.votacao_api.domains.Setor;
import br.com.ueder.votacao_api.dto.AtualizaDadosPessoaDTO;
import br.com.ueder.votacao_api.dto.PessoaDTO;
import br.com.ueder.votacao_api.exceptions.ObjectNotFoundException;
import br.com.ueder.votacao_api.repositories.CargoRepository;
import br.com.ueder.votacao_api.repositories.PessoaRepository;
import br.com.ueder.votacao_api.repositories.SetorRepository;
import br.com.ueder.votacao_api.services.CargoService;
import br.com.ueder.votacao_api.services.PessoaService;
import br.com.ueder.votacao_api.services.SetorService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaConverter {

    private PessoaService pessoaService;
    private CargoService cargoService;
    private SetorService setorService;

    public PessoaConverter(PessoaService pessoaService, CargoService cargoService, SetorService setorService){
        this.pessoaService = pessoaService;
        this.cargoService = cargoService;
        this.setorService = setorService;
    }

    public PessoaDTO toPessoaDTO(AtualizaDadosPessoaDTO atualizaDTO){
        Pessoa pessoa = pessoaService.findById(String.valueOf(atualizaDTO.id()));
        Cargo cargo = cargoService.findById(String.valueOf(atualizaDTO.cargo()));
        Setor setor = setorService.findById(String.valueOf(atualizaDTO.setor()));
        pessoa.setCargo(cargo);
        pessoa.setSetor(setor);
        pessoa.setNome(atualizaDTO.nome());
        pessoa.setFoto(atualizaDTO.foto());
        pessoa.desligarPessoa(atualizaDTO.dataRescisao());
        return new PessoaDTO(pessoa);
    }

}
