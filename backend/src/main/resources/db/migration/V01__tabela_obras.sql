CREATE TABLE obra(
    id UUID PRIMARY KEY,
    nome_obra VARCHAR(255),
    codigo_obra VARCHAR(255),
    tipo_obra VARCHAR(255),
    status_obra VARCHAR(255),
    art VARCHAR(255) UNIQUE,
    responsavel_tecnico VARCHAR(255),
    responsavel_da_obra VARCHAR(255),
    cei_cno VARCHAR(255),
    area_total DECIMAL(10, 2),
    unidade_medida VARCHAR(255),
    comentario VARCHAR(255),
    obra_visivel_para VARCHAR(255),
    status_lancamentos BOOLEAN,
    status_faturamentos BOOLEAN,
    status_compras BOOLEAN
);
