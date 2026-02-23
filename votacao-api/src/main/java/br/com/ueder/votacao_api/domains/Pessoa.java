package br.com.ueder.votacao_api.domains;

import br.com.ueder.votacao_api.dto.CargoDTO;
import br.com.ueder.votacao_api.dto.DadosPessoaDTO;
import br.com.ueder.votacao_api.dto.PessoaDTO;
import br.com.ueder.votacao_api.dto.SetorDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nome;

    @Column(name = "matricula", nullable = false, length = 20)
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cargo_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_cargo_pessoa"))
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "setor_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_setor_pessoa"))
    private Setor setor;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(name = "data_rescisao")
    private LocalDate dataRescisao;

    @Column(name = "path_foto", nullable = false, length = 255)
    private String foto;

    @Column(name = "data_cad", nullable = false)
    private LocalDateTime dataCriacao =  LocalDateTime.now();

    @Column(name = "data_alt")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Pessoa() {}

    public Pessoa(DadosPessoaDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.matricula = dto.matricula();
        this.cargo = new CargoDTO(dto.cargo(), "").toModel();
        this.setor = new SetorDTO(dto.setor(), "").toModel();
        this.dataAdmissao = dto.dataAdmissao();
        this.dataRescisao = dto.dataRescisao();
        this.foto = dto.foto();
    }

    public Pessoa(PessoaDTO dto) {
        this.nome = dto.nome();
        this.matricula = dto.matricula();
        this.cargo = dto.cargo().toModel();
        this.setor = dto.setor().toModel();
        this.dataAdmissao = dto.dataAdmissao();
        this.dataRescisao = dto.dataRescisao();
        this.foto = dto.foto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataRescisao() {
        return dataRescisao;
    }

    public void desligarPessoa(LocalDate dataRescisao) {
        this.dataRescisao = dataRescisao;
        this.ativo = false;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void excluir() {
        this.ativo = false;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

}
