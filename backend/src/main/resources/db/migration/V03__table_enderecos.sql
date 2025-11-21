CREATE TABLE endereco
(
    id          UUID PRIMARY KEY,
    cep         VARCHAR(255),
    endereco    VARCHAR(255),
    numero      VARCHAR(255),
    complemento VARCHAR(255),
    bairro      VARCHAR(255),
    cidade      VARCHAR(255),
    estado      VARCHAR(255),
    CONSTRAINT uk_endereco_cep_numero UNIQUE (cep, numero)
);
