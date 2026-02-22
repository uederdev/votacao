package br.com.ueder.votacao_api.services;

import br.com.ueder.votacao_api.domains.Cargo;
import br.com.ueder.votacao_api.dto.CargoDTO;
import br.com.ueder.votacao_api.exceptions.ObjectNotFoundException;
import br.com.ueder.votacao_api.repositories.CargoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargoService implements IService<Cargo, CargoDTO> {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }


    @Override
    @Transactional
    public Cargo save(CargoDTO entity) {
        return cargoRepository.save(entity.toModel());
    }

    @Override
    public Cargo update(CargoDTO entity) {
        Cargo cargoEncontrado = findById(String.valueOf(entity.id()));
        cargoEncontrado.setDescricao(entity.descricao());
        return cargoRepository.save(cargoEncontrado);
    }

    @Override
    public void delete(CargoDTO entity) {
        Cargo cargoEncontrado = findById(String.valueOf(entity.id()));
        cargoEncontrado.excluir();
        cargoRepository.save(cargoEncontrado);
    }

    @Override
    public Cargo findById(String id) {
        return cargoRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll(true);
    }

    @Override
    public List<Cargo> findByDescricao(String descricao) {
        return cargoRepository.findByDescricao(descricao, true);
    }
}
