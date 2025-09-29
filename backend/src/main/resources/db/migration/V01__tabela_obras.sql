CREATE TABLE status_obra
(
    id   SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE tipo_obra
(
    id   SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE unidade_medida
(
    id   SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE obra
(
    id                  UUID PRIMARY KEY,
    nome                VARCHAR(255),
    codigo              VARCHAR(255),
    art                 VARCHAR(255) UNIQUE,
    responsavel_tecnico VARCHAR(255),
    responsavel_obra    VARCHAR(255),
    cei_cno             VARCHAR(255),
    area_total          DECIMAL(10, 2),
    comentario          VARCHAR(255),
    status_lancamentos  BOOLEAN,
    status_faturamentos BOOLEAN,
    status_compras      BOOLEAN,
    tipo_obra_id        INT NOT NULL,
    status_obra_id      INT NOT NULL,
    unidade_medida_id   INT NOT NULL,
    CONSTRAINT fk_obra_tipo FOREIGN KEY (tipo_obra_id) REFERENCES tipo_obra (id),
    CONSTRAINT fk_obra_status FOREIGN KEY (status_obra_id) REFERENCES status_obra (id),
    CONSTRAINT fk_obra_unidade_medida FOREIGN KEY (unidade_medida_id) REFERENCES unidade_medida (id)
);