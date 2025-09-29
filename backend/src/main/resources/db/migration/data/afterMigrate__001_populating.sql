INSERT INTO status_obra (nome)
VALUES ('PLANEJAMENTO'),
       ('EM_ANDAMENTO'),
       ('PAUSADA'),
       ('CONCLUIDA'),
       ('CANCELADA') ON CONFLICT DO NOTHING;

INSERT INTO tipo_obra (nome)
VALUES ('RESIDENCIAL'),
       ('COMERCIAL'),
       ('INDUSTRIAL'),
       ('INFRAESTRUTURA') ON CONFLICT DO NOTHING;

INSERT INTO unidade_medida (nome)
VALUES ('M2') ON CONFLICT DO NOTHING;

INSERT INTO obra (id, nome, codigo, art, responsavel_tecnico, responsavel_obra, cei_cno, area_total, comentario,
                  status_lancamentos, status_faturamentos, status_compras, tipo_obra_id, status_obra_id,
                  unidade_medida_id)
VALUES (gen_random_uuid(), 'Edifício Corporativo Alpha', 'ECA-001', '112233445', 'Carlos Mendes',
        'Investimentos Prime Ltda', '987654321098', 8500.00, 'Fase de aprovação do projeto arquitetônico.',
        FALSE, FALSE, FALSE,
        (SELECT id FROM tipo_obra WHERE nome = 'COMERCIAL'),
        (SELECT id FROM status_obra WHERE nome = 'PLANEJAMENTO'),
        (SELECT id FROM unidade_medida WHERE nome = 'M2')) ON CONFLICT DO NOTHING;

INSERT INTO obra (id, nome, codigo, art, responsavel_tecnico, responsavel_obra, cei_cno, area_total, comentario,
                  status_lancamentos, status_faturamentos, status_compras, tipo_obra_id, status_obra_id,
                  unidade_medida_id)
VALUES (gen_random_uuid(), 'Galpão Logístico Sul', 'GLS-003', '556677889', 'Ana Paula Costa', 'Logística Total S.A.',
        '543210987654', 12350.50, 'Obra entregue em 15/08/2025. Pendente documentação final.',
        FALSE, FALSE, FALSE,
        (SELECT id FROM tipo_obra WHERE nome = 'INDUSTRIAL'),
        (SELECT id FROM status_obra WHERE nome = 'CONCLUIDA'),
        (SELECT id FROM unidade_medida WHERE nome = 'M2')) ON CONFLICT DO NOTHING;

INSERT INTO obra (id, nome, codigo, art, responsavel_tecnico, responsavel_obra, cei_cno, area_total, comentario,
                  status_lancamentos, status_faturamentos, status_compras, tipo_obra_id, status_obra_id,
                  unidade_medida_id)
VALUES (gen_random_uuid(), 'Pavimentação Acesso Norte', 'PAN-002', '998877665', 'João da Silva', 'Prefeitura Municipal',
        '102938475610', 4200.00, 'Pausada devido a reajuste orçamentário. Previsão de retorno em 3 meses.',
        TRUE, FALSE, FALSE,
        (SELECT id FROM tipo_obra WHERE nome = 'INFRAESTRUTURA'),
        (SELECT id FROM status_obra WHERE nome = 'PAUSADA'),
        (SELECT id FROM unidade_medida WHERE nome = 'M2')) ON CONFLICT DO NOTHING;

INSERT INTO obra (id, nome, codigo, art, responsavel_tecnico, responsavel_obra, cei_cno, area_total, comentario,
                  status_lancamentos, status_faturamentos, status_compras, tipo_obra_id, status_obra_id,
                  unidade_medida_id)
VALUES (gen_random_uuid(), 'Condomínio Vista Verde', 'CVV-004', '123123123', 'Fernando Lima',
        'Construtora Morar Bem', '657483920123', 6800.00,
        'Projeto cancelado por questões de licenciamento ambiental.',
        FALSE, FALSE, FALSE,
        (SELECT id FROM tipo_obra WHERE nome = 'RESIDENCIAL'),
        (SELECT id FROM status_obra WHERE nome = 'CANCELADA'),
        (SELECT id FROM unidade_medida WHERE nome = 'M2')) ON CONFLICT DO NOTHING;

INSERT INTO obra (id, nome, codigo, art, responsavel_tecnico, responsavel_obra, cei_cno, area_total, comentario,
                  status_lancamentos, status_faturamentos, status_compras, tipo_obra_id, status_obra_id,
                  unidade_medida_id)
VALUES (gen_random_uuid(), 'Torres do Parque - Torre B', 'TPB-001', '321654987', 'Mariana Souza',
        'Sra. Helena Costa', '213456789012', 340.80,
        'Fase de acabamentos. 75% concluído.',
        TRUE, TRUE, TRUE,
        (SELECT id FROM tipo_obra WHERE nome = 'RESIDENCIAL'),
        (SELECT id FROM status_obra WHERE nome = 'EM_ANDAMENTO'),
        (SELECT id FROM unidade_medida WHERE nome = 'M2')) ON CONFLICT DO NOTHING;