create table candidatos (
    id bigserial primary key not null,
    pessoa_id bigint not null,
    numero int not null,
    ativo boolean not null default true,
    data_cad timestamp not null default current_timestamp,
    data_alt timestamp,
    constraint fk_candidato_pessoa foreign key (pessoa_id) references pessoas (id)
)