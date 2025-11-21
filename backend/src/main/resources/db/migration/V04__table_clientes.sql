CREATE TABLE cliente
(
    id                  UUID PRIMARY KEY,
    nome                VARCHAR(255),
    razao_social        VARCHAR(255),
    tipo                VARCHAR(255),
    cpf                 VARCHAR(255),
    cnpj                VARCHAR(255),
    inscricao_estadual  VARCHAR(255),
    inscricao_municipal VARCHAR(255),
    telefone_1          VARCHAR(255),
    telefone_2          VARCHAR(255),
    email               VARCHAR(255),
    comentario          VARCHAR(255),
    endereco_id         UUID,
    CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco (id),
    CONSTRAINT uk_cpf UNIQUE (cpf),
    CONSTRAINT uk_cnpj UNIQUE (cnpj)
);