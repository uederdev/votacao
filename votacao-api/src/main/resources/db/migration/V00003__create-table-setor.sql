/* CRIA TABELA SETORES */
CREATE TABLE setores
(
    id serial NOT NULL ,
    descricao character varying(80) NOT NULL,
    ativo boolean NOT NULL DEFAULT true,
    data_cad timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alt timestamp ,
    CONSTRAINT setores_pk PRIMARY KEY (id)
)
