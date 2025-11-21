INSERT INTO endereco (id, cep, endereco, numero, complemento, bairro, cidade, estado)
VALUES
    (gen_random_uuid(), '01311-000', 'Avenida Paulista', '1578', 'Andar 10', 'Bela Vista', 'São Paulo', 'SP'),
    (gen_random_uuid(), '22070-001', 'Avenida Atlântica', '2964', 'Apto 502', 'Copacabana', 'Rio de Janeiro', 'RJ'),
    (gen_random_uuid(), '80420-090', 'Rua Comendador Araújo', '510', 'Sala 3', 'Centro', 'Curitiba', 'PR')
ON CONFLICT DO NOTHING;

INSERT INTO cliente (id, nome, razao_social, tipo, cpf, cnpj, inscricao_estadual, inscricao_municipal, telefone_1,
                     telefone_2, email, comentario, endereco_id)
VALUES
    (gen_random_uuid(), 'Construtora Morar Bem', 'Morar Bem Construções e Incorporações Ltda.', 'PESSOA_JURIDICA',
     NULL, '01.234.567/0001-89', '123.456.789.112', '9.876.543-2',
     '(11) 3333-4444', '(11) 99999-8888', 'contato@morarbem.com',
     'Cliente antigo, parceiro em grandes projetos.',
     (SELECT id FROM endereco WHERE cep = '01311-000' AND numero = '1578' AND complemento = 'Andar 10' LIMIT 1)
    )
ON CONFLICT DO NOTHING;

INSERT INTO cliente (id, nome, razao_social, tipo, cpf, cnpj, inscricao_estadual, inscricao_municipal, telefone_1,
                     telefone_2, email, comentario, endereco_id)
VALUES
    (gen_random_uuid(), 'Sra. Helena Costa', NULL, 'PESSOA_FISICA',
     '123.456.789-00', NULL, NULL, NULL,
     '(11) 5555-1234', '(11) 98765-4321', 'helena.costa@email.com',
     'Proprietária da obra Torres do Parque - Torre B.',
     (SELECT id FROM endereco WHERE cep = '01311-000' AND numero = '1578' AND complemento = 'Andar 10' LIMIT 1)
    )
ON CONFLICT DO NOTHING;

INSERT INTO cliente (id, nome, razao_social, tipo, cpf, cnpj, inscricao_estadual, inscricao_municipal, telefone_1,
                     telefone_2, email, comentario, endereco_id)
VALUES
    (gen_random_uuid(), 'Investimentos Prime', 'Investimentos Prime Ltda.', 'PESSOA_JURIDICA',
     NULL, '11.222.333/0001-44', '987.654.321.110', '1.234.567-8',
     '(21) 2222-3333', NULL, 'financeiro@investprime.com',
     'Responsável pela obra Edifício Corporativo Alpha.',
     (SELECT id FROM endereco WHERE cep = '22070-001' AND numero = '2964' AND complemento = 'Apto 502' LIMIT 1)
    )
ON CONFLICT DO NOTHING;

INSERT INTO cliente (id, nome, razao_social, tipo, cpf, cnpj, inscricao_estadual, inscricao_municipal, telefone_1,
                     telefone_2, email, comentario, endereco_id)
VALUES
    (gen_random_uuid(), 'Logística Total', 'Logística Total S.A.', 'PESSOA_JURIDICA',
     NULL, '22.333.444/0001-55', '246.813.579.111', '8.765.432-1',
     '(41) 3030-4040', '(41) 97777-6666', 'operacoes@logisticatotal.com.br',
     'Contrato para construção de múltiplos galpões.',
     (SELECT id FROM endereco WHERE cep = '80420-090' AND numero = '510' AND complemento = 'Sala 3' LIMIT 1)
    )
ON CONFLICT DO NOTHING;

INSERT INTO cliente (id, nome, razao_social, tipo, cpf, cnpj, inscricao_estadual, inscricao_municipal, telefone_1,
                     telefone_2, email, comentario, endereco_id)
VALUES
    (gen_random_uuid(), 'Fernando Lima', NULL, 'PESSOA_FISICA',
     '987.654.321-11', NULL, NULL, NULL,
     '(41) 98888-7777', NULL, 'fernando.lima.eng@email.com',
     'Engenheiro responsável pela Condomínio Vista Verde.',
     (SELECT id FROM endereco WHERE cep = '80420-090' AND numero = '510' AND complemento = 'Sala 3' LIMIT 1)
    )
ON CONFLICT DO NOTHING;
