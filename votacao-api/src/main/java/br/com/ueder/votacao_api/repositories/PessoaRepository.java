package br.com.ueder.votacao_api.repositories;

import br.com.ueder.votacao_api.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from Pessoa p join fetch p.cargo c join fetch p.setor s where p.ativo = :ativo and p.id = :id")
    Optional<Pessoa> findById(Long id, boolean ativo);

    @Query("select p from Pessoa p join fetch p.cargo c join fetch p.setor s where p.ativo = :ativo")
    List<Pessoa> findAll(boolean ativo);

    @Query("select p from Pessoa p where p.ativo = :ativo and p.matricula = :matricula")
    Optional<Pessoa> findByMatricula(String matricula, boolean ativo);


}
