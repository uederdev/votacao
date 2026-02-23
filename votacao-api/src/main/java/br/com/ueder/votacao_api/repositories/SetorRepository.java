package br.com.ueder.votacao_api.repositories;

import br.com.ueder.votacao_api.domains.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query("select s from Setor s where s.ativo = :ativo")
    List<Setor> findAll(Boolean ativo);

    @Query("select s from Setor s where s.ativo = :ativo and upper(s.descricao) like upper(:descricao)")
    List<Setor> findByDescricao(String descricao, Boolean ativo);

    @Query("select s from Setor s where s.ativo = :ativo and s.id = :id")
    Optional<Setor> findById(Integer id, boolean ativo);
}
