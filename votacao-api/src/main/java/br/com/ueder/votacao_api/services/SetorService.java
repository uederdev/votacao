package br.com.ueder.votacao_api.services;

import br.com.ueder.votacao_api.domains.Setor;
import br.com.ueder.votacao_api.dto.SetorDTO;
import br.com.ueder.votacao_api.exceptions.ObjectNotFoundException;
import br.com.ueder.votacao_api.repositories.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetorService implements IService<Setor, SetorDTO> {

    private final SetorRepository repository;

    public SetorService(SetorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Setor save(SetorDTO entity) {
        Setor setor = entity.toModel();
        return repository.save(setor);
    }

    @Override
    @Transactional
    public Setor update(SetorDTO entity) {
        Setor setor = findById(String.valueOf(entity.id()));
        setor.setDescricao(entity.descricao());
        return repository.save(setor);
    }

    @Override
    @Transactional
    public void delete(SetorDTO entity) {
        Setor setorEncontrado = findById(String.valueOf(entity.id()));
        setorEncontrado.excluir();
        repository.save(setorEncontrado);
    }

    @Override
    public Setor findById(String id) {
        return repository.findById(Integer.valueOf(id), true)
                .orElseThrow(() -> new ObjectNotFoundException("Setor -> " + id));
    }

    @Override
    public List<Setor> findAll() {
        return repository.findAll(true);
    }

    @Override
    public List<Setor> findByDescricao(String descricao) {
        return repository.findByDescricao(descricao +"%",  true);
    }
}
