package br.com.ueder.votacao_api.domains;

import java.time.LocalDateTime;

public class Candidato extends Pessoa {

    private Long id;
    private String nome;
    private Pessoa pessoa;
    private Boolean ativo;
    private Integer numero;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;



}
