package br.com.ueder.votacao_api.domains;

import br.com.ueder.votacao_api.dto.SetorDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "setores")
public class Setor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", unique = true, nullable = false, length = 80)
    private String descricao;

    @Column(name = "data_cad", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "data_alt")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Setor() { }

    public Setor(SetorDTO dto) {
        this.id = dto.id();
        this.descricao = dto.descricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    @PreUpdate
    public void update() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void excluir() {
        this.ativo = false;
        this.dataAtualizacao = LocalDateTime.now();
    }
}
