INSERT INTO obra (id, nome_obra, codigo_obra, tipo_obra, status_obra, art, responsavel_tecnico,
                  responsavel_da_obra, cei_cno, area_total, unidade_medida, quem_paga, conta,
                  comentario, obra_visivel_para, status_lancamentos, status_faturamentos,
                  status_compras)
VALUES (gen_random_uuid(), 'Edifício Corporativo Alpha', 'ECA-001', 'COMERCIAL', 'PLANEJAMENTO', '112233445',
        'Carlos Mendes',
        'Investimentos Prime Ltda', '987654321098', 8500.00, 'M2', 'EMPRESA', 'Conta Investimento - 98765-4',
        'Fase de aprovação do projeto arquitetônico.', 'DIRETORES, ARQUITETOS', FALSE, FALSE, FALSE);

INSERT INTO obra (id, nome_obra, codigo_obra, tipo_obra, status_obra, art, responsavel_tecnico,
                  responsavel_da_obra, cei_cno, area_total, unidade_medida, quem_paga, conta,
                  comentario, obra_visivel_para, status_lancamentos, status_faturamentos,
                  status_compras)
VALUES (gen_random_uuid(), 'Galpão Logístico Sul', 'GLS-003', 'INDUSTRIAL', 'CONCLUIDA', '556677889', 'Ana Paula Costa',
        'Logística Total S.A.', '543210987654', 12350.50, 'M2', 'CLIENTE', 'Conta Projeto GLS - 11223-3',
        'Obra entregue em 15/08/2025. Pendente documentação final.', 'TODOS', FALSE, FALSE, FALSE);

INSERT INTO obra (id, nome_obra, codigo_obra, tipo_obra, status_obra, art, responsavel_tecnico,
                  responsavel_da_obra, cei_cno, area_total, unidade_medida, quem_paga, conta,
                  comentario, obra_visivel_para, status_lancamentos, status_faturamentos,
                  status_compras)
VALUES (gen_random_uuid(), 'Pavimentação Acesso Norte', 'PAN-002', 'INFRAESTRUTURA', 'PAUSADA', '998877665',
        'João da Silva',
        'Prefeitura Municipal', '102938475610', 4200.00, 'M2', 'CLIENTE', 'Conta Obras Públicas - 55443-2',
        'Pausada devido a reajuste orçamentário. Previsão de retorno em 3 meses.', 'ENGENHEIROS, FISCAIS', TRUE, FALSE,
        FALSE);


INSERT INTO obra (id, nome_obra, codigo_obra, tipo_obra, status_obra, art, responsavel_tecnico,
                  responsavel_da_obra, cei_cno, area_total, unidade_medida, quem_paga, conta,
                  comentario, obra_visivel_para, status_lancamentos, status_faturamentos,
                  status_compras)
VALUES (gen_random_uuid(), 'Condomínio Vista Verde', 'CVV-004', 'RESIDENCIAL', 'CANCELADA', '123123123',
        'Fernando Lima',
        'Construtora Morar Bem', '657483920123', 6800.00, 'M2', 'EMPRESA', 'Conta CVV - 78901-2',
        'Projeto cancelado por questões de licenciamento ambiental.', 'DIRETORES', FALSE, FALSE, FALSE);


INSERT INTO obra (id, nome_obra, codigo_obra, tipo_obra, status_obra, art, responsavel_tecnico,
                  responsavel_da_obra, cei_cno, area_total, unidade_medida, quem_paga, conta,
                  comentario, obra_visivel_para, status_lancamentos, status_faturamentos,
                  status_compras)
VALUES (gen_random_uuid(), 'Torres do Parque - Torre B', 'TPB-001', 'RESIDENCIAL', 'EM_ANDAMENTO', '321654987',
        'Mariana Souza',
        'Sra. Helena Costa', '213456789012', 340.80, 'M2', 'CLIENTE', 'Conta Corrente - 45678-9',
        'Fase de acabamentos. 75% concluído.', 'ENGENHEIROS, CLIENTE, MESTRE_DE_OBRAS', TRUE, TRUE, TRUE);