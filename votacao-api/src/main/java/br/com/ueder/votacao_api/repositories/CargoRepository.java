package br.com.ueder.votacao_api.repositories;

import br.com.ueder.votacao_api.domains.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    @Query(value = "select c from Cargo c where c.ativo = :ativo")
    List<Cargo> findByDescricao(String descricao, Boolean ativo);

    @Query(value = "select c from Cargo c where c.ativo = :ativo")
    List<Cargo> findAll(Boolean ativo);



}
