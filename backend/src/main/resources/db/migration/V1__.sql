CREATE TABLE "real-estate-monitoring".funcionarios
(
    id                UUID        NOT NULL,
    nome              VARCHAR(255),
    sexo              VARCHAR(255),
    cpf               VARCHAR(11) NOT NULL,
    rg                VARCHAR(15) NOT NULL,
    uf                VARCHAR(255),
    telefone_1        VARCHAR(255),
    telefone_2        VARCHAR(255),
    email             VARCHAR(255),
    data_nascimento   date,
    quantidade_filhos INTEGER,
    nome_mae          VARCHAR(255),
    nome_pai          VARCHAR(255),
    is_terceiro       BOOLEAN,
    CONSTRAINT pk_funcionarios PRIMARY KEY (id)
);

CREATE TABLE "real-estate-monitoring".obra
(
    id UUID NOT NULL,
    CONSTRAINT pk_obra PRIMARY KEY (id)
);

ALTER TABLE "real-estate-monitoring".funcionarios
    ADD CONSTRAINT uc_funcionarios_cpf UNIQUE (cpf);