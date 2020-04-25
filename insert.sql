##SCRIPTS DOS INSERTS
##NA TABELA FUNCAO
INSERT INTO funcao(descricao)
VALUES('ADMIN');
##SELECT * FROM funcao;
##NA TABELA ESTABELECIMENTO
INSERT INTO estabelecimento(cnpj, nome, cep, numero, cidade, bairro, estado, pais)
VALUES('88.784.557/0001-11', 'Vendinha do Verde', '72311-300', 1620, 'São Paulo', 'Centro', 'SP', 'Brasil');
##SELECT * FROM estabelecimento;
##NA TABELA ESTABELECIMENTO_TELEFONE
INSERT INTO estabelecimento_telefone(estabelecimento, numero)
VALUES(LAST_INSERT_ID(), '011 22223333');
##SELECT * FROM estabelecimento_telefone;
##NA TABELA FUNCIONARIO
INSERT INTO funcionario(nome,
								sobrenome,
								cep,
								numero,
								cidade,
								bairro,
								estado,
								pais,
								email,
								funcao,
								estabelecimento,
								cpf)
VALUES(	'João',
		 	'Silva',
		 	'09401-060',
		 	123,
		 	'São Paulo',
		 	'Centro',
		 	'SP',
		 	'Brasil',
		 	'joaoSilva@email.com',
		 	1,
		 	1,
		 	'438.814.708/75');
##SELECT * FROM funcionario;
##NA TABELA FUNCIONARIO_TELEFONE
INSERT INTO funcionario_telefone(funcionario, numero)
VALUES(LAST_INSERT_ID(), '011 999665544');
##SELECT * FROM funcionario_telefone;
##NA TABELA SETOR
INSERT INTO setor(nome, estabelecimento)
VALUES('Lactose', 1);
##SELECT * FROM setor;
##NA TABELA ESTOQUE
INSERT INTO estoque(	codProduto,
							estabelecimento,
							nome,
							qtdProduto,
							precoVarejo,
							descontoAtacado,
							qtdAtacado,
							setor)
VALUES(	'7896066804361',
			1,
			'TEIXEIRA Manteiga de Primeira qualidade com sal',
			500,
			5.60,
			NULL,
			NULL,
			2);
##SELECT * FROM estoque;
##NA TABELA LOTE
INSERT INTO lote(codLote, estoque, dtmVencimento, qtdProduto, situacao, vlrPago)
VALUES('AA34151', LAST_INSERT_ID(), NOW() + INTERVAL 1 YEAR, 500, 'PRATELEIRA', 1000.00);
##SELECT * FROM lote;
##SHOW CREATE TABLE lote;
##NA TABELA CAIXA
INSERT INTO caixa(tipo, descricao, vlrDinheiro)
values('OUT', 'COMPRA', 1000.00);
##SELECT * FROM caixa;
##SHOW CREATE TABLE caixa;
##NA TABELA COMPRA
INSERT INTO compra(idCaixa, idLote)
VALUES(LAST_INSERT_ID(), 1);
##SELECT * FROM compra;
##Preparação para uma venda
INSERT INTO caixa(tipo, descricao, vlrDinheiro)
VALUES('IN', 'VENDA', 5.60);
##SELECT * FROM caixa;
INSERT INTO venda(idCaixa, idLote)
VALUES(2, 1);
##SELECT * FROM venda;
