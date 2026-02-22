package br.com.ueder.votacao_api.services;

import java.util.List;

public interface IService<M, D> {

    M save(D entity);
    M update(D entity);
    void delete(D entity);
    M findById(String id);
    List<M> findAll();
    List<M> findByDescricao(String descricao);

}
