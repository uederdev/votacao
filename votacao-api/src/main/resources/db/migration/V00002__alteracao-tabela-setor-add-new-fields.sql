alter table cargos add ativo boolean not null default true;
alter table cargos add data_cad timestamp not null default current_timestamp;
alter table cargos add data_alt timestamp;

