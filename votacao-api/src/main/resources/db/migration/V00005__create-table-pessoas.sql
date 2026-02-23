create table pessoas (
    id bigserial not null primary key,
    nome_completo varchar(100) not null,
    matricula varchar(20) not null,
    cargo_id bigint not null,
    setor_id bigint not null,
    data_admissao date not null,
    data_rescisao date,
    path_foto varchar(255),
    data_cad timestamp default CURRENT_TIMESTAMP not null,
    data_alt timestamp,
    ativo boolean not null default true,
    constraint fk_cargo_pessoa foreign key (cargo_id) references cargos(id),
    constraint fk_setor_pessoa foreign key (setor_id) references setores(id)
)