package br.com.ueder.votacao_api.services;

import br.com.ueder.votacao_api.domains.Cargo;
import br.com.ueder.votacao_api.domains.Pessoa;
import br.com.ueder.votacao_api.domains.Setor;
import br.com.ueder.votacao_api.dto.AtualizaDadosPessoaDTO;
import br.com.ueder.votacao_api.dto.PessoaDTO;
import br.com.ueder.votacao_api.exceptions.ObjectNotFoundException;
import br.com.ueder.votacao_api.exceptions.RegistroDuplicadoException;
import br.com.ueder.votacao_api.repositories.PessoaRepository;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final CargoService cargoService;
    private final SetorService setorService;

    public PessoaService(PessoaRepository pessoaRepository, CargoService cargoService, SetorService setorService) {
        this.pessoaRepository = pessoaRepository;
        this.cargoService = cargoService;
        this.setorService = setorService;
    }

    @Transactional
    public Pessoa save(PessoaDTO entity) {
        var pessoa = entity.toModel();
        if (pessoaRepository.findByMatricula(entity.matricula(), true).isPresent()) {
            throw new RegistroDuplicadoException("Matricula " + entity.matricula());
        }
        cargoService.findById(String.valueOf(pessoa.getCargo().getId()));
        setorService.findById(String.valueOf(pessoa.getSetor().getId()));
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void delete(Long id) {
        Pessoa pessoaEncontrada = findById(String.valueOf(id));
        pessoaEncontrada.excluir();
        pessoaRepository.save(pessoaEncontrada);
    }

    public Pessoa findById(String id) {
        return pessoaRepository.findById(Long.parseLong(id), true)
                .orElseThrow(() -> new ObjectNotFoundException("Pessoa -> " + id));
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll(true);
    }

    public List<Pessoa> findByDescricao(String descricao) {
        return List.of();
    }

    @Transactional
    public Pessoa update(AtualizaDadosPessoaDTO entity) {
        Pessoa pessoa = findById(String.valueOf(entity.id()));
        Cargo cargo = cargoService.findById(String.valueOf(entity.cargo()));
        Setor setor = setorService.findById(String.valueOf(entity.setor()));
        pessoa.setCargo(cargo);
        pessoa.setSetor(setor);
        pessoa.setNome(entity.nome());
        if (entity.dataRescisao() != null){
            pessoa.desligarPessoa(entity.dataRescisao());
        }
        if (entity.foto() != null) {
            pessoa.setFoto(entity.foto());
        }
        return pessoaRepository.save(pessoa);
    }
}
