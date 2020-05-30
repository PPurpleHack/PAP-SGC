############################################Script Principal#################################################
##CRIA A TABELA DE FUNÇÕES DOS FUNCIONARIOS NA EMPRESA
CREATE TABLE funcao(
	idFuncao INT(2) NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(16) NOT NULL,
	CONSTRAINT pk_funcao PRIMARY KEY(idFuncao)
);
##INSERE A FUNÇÃO MASTER COMO PADRÃO PARA A PRIMEIRA CONTA (PODE SER CONSIDERADO COMO ADMIN)
INSERT INTO funcao(descricao)
VALUES('MASTER');

##CRIA TABELA ESTABELCIMENTO
CREATE TABLE estabelecimento(
	idEstabelecimento INT(4) NOT NULL AUTO_INCREMENT,
	cnpj VARCHAR(18) NOT NULL,
	nome VARCHAR(25),
	cep VARCHAR(9) NOT NULL,
	numero INT(4) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	bairro VARCHAR(30) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	pais VARCHAR(30) NOT NULL,
	CONSTRAINT pk_estabelecimento PRIMARY KEY(idEstabelecimento)
);

##CRIA TABELA QUE ARMAZENA OS TELEFONES DOS ESTABELECIMENTOS
CREATE TABLE estabelecimento_telefone(
	idEstabTelefone INT(4) NOT NULL AUTO_INCREMENT,
	estabelecimento INT(4) NOT NULL,
  `ddd` VARCHAR(3) NOT NULL,
  `numero` VARCHAR(9) NOT NULL,
  `celular` BOOLEAN NOT NULL,
	CONSTRAINT pk_estab_telefone PRIMARY KEY(idEstabTelefone),
	CONSTRAINT fk_estab FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento)
);

##CRIA TABELA FUNCIONARIO QUE TAMBÉM SERÁ A TABELA AONDE ESTARÁ O LOGIN
CREATE TABLE funcionario(
	matricula INT(4) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	sobrenome VARCHAR(20) NOT NULL,
	cep VARCHAR(9) NOT NULL,
	numero INT(4) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	bairro VARCHAR(30) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	pais VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL,
	login INT(4),
	senha VARCHAR(40),
	funcao INT(2) NOT NULL,
	estabelecimento INT(4),
	CONSTRAINT pk_matricula PRIMARY KEY(matricula),
	CONSTRAINT fk_funcao FOREIGN KEY(funcao) REFERENCES funcao(idfuncao),
	CONSTRAINT fk_estabelecimento FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento)
);
ALTER TABLE funcionario
ADD COLUMN cpf VARCHAR(15) NOT NULL;

##CRIA TABELA QUE ARMAZENA O TELEFONE DOS FUNCIONARIOS
CREATE TABLE funcionario_telefone(
	idFuncTelefone INT(4) NOT NULL AUTO_INCREMENT,
	funcionario INT(4) NOT NULL,
	`ddd` VARCHAR(3) NOT NULL,
  `numero` VARCHAR(9) NOT NULL,
  `celular` BOOLEAN NOT NULL,
	CONSTRAINT pk_func_telefone PRIMARY KEY(idFuncTelefone),
	CONSTRAINT fk_func FOREIGN KEY(funcionario) REFERENCES funcionario(matricula)
);

##CRIA A TABELA SETOR, REFERENTE AO SETOR QUE É ALOCADO O PRODUTO
CREATE TABLE setor(
	idSetor INT(3) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	estabelecimento INT(4) NOT NULL,
	CONSTRAINT pk_setor PRIMARY KEY(idSetor),
	CONSTRAINT fk_estabelecimento_ FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento)
);

##CRIA A TABELA ESTOQUE
##A CHAVE PRIMARIA É COMPOSTA PELO CODIGO DO PRODUTO E O ESTABELECIMENTO
CREATE TABLE estoque(
	idEstoque INT(11) NOT NULL AUTO_INCREMENT,
	codProduto varchar(14) NOT NULL,
	estabelecimento INT(4) NOT NULL,
	nome VARCHAR(30) NOT NULL,
	qtdProduto INT(6) NOT NULL,
	precoVarejo DOUBLE(7,2) NOT NULL,
	descontoAtacado DOUBLE(3,2),##Desconto que sera feito no produto quando for atacado em porcentagem
	qtdAtacado INT(3),##Quantidade de itens para serem considerado atacado
	setor INT(3),
	CONSTRAINT pk_estoque PRIMARY KEY(idEstoque)
);
ALTER TABLE estoque
ADD CONSTRAINT fk_estab_estoque FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento);
ALTER TABLE estoque
MODIFY COLUMN nome VARCHAR(200) NOT NULL,
ALTER TABLE estoque
MODIFY COLUMN descontoAtacado DOUBLE(4,2) DEFAULT NULL;
ALTER TABLE estoque
ADD COLUMN situacao VARCHAR(11) DEFAULT NULL;
##CRIAÇÃO DA TABELA LOTE
##AS COMPRAS SÃO FEITAS EM LOTE, LOGO AQUI SERÃO GUARDADAS AS INFORMAÇÕES DE LOTE
CREATE TABLE lote(
	idLote INT(11) NOT NULL AUTO_INCREMENT,
	codLote VARCHAR(14) NOT NULL,
	estoque INT(11) NOT NULL,
	dtmVencimento DATE,
	qtdProduto DOUBLE(7,2) NOT NULL,
	situacao VARCHAR(10) NOT NULL,##Possiveis situações: ESTOQUE, PRATELEIRA, ESGOTADO ou PERDIDO
	vlrPago DOUBLE(11,2) NOT NULL,
	CONSTRAINT pk_lote PRIMARY KEY(idLote),
	CONSTRAINT fk_estoque FOREIGN KEY(estoque) REFERENCES estoque(idEstoque),
	CONSTRAINT ck_situacao CHECK(situacao IN('ESTOQUE', 'PRATELEIRA', 'ESGOTADO', 'PERDIDO'))
);
ALTER TABLE lote
MODIFY COLUMN qtdProduto INT(6) NOT NULL;

##CRIAÇÃO DA TABELA CAIXA
##ESTÁ TABELA GUARDA OS REGISTROS RESUMIDOS DO FLUXO DO CAIXA, ENTRADA E SAIDA DE DINHEIRO
CREATE TABLE caixa(
	idCaixa INT(11) NOT NULL AUTO_INCREMENT,
	tipo VARCHAR(3) NOT NULL,##Possiveis valores: IN(Entrada de caixa), OUT(Saida de caixa)
	descricao VARCHAR(6),#Possiveis valores: COMPRA, VENDA E PERCA
	vlrDinheiro DOUBLE(11,2) NOT NULL,
	CONSTRAINT pk_caixa PRIMARY KEY(idCaixa),
	CONSTRAINT ck_descricao CHECK(descricao = 'COMPRA' OR descricao = 'VENDA' OR descricao = 'PERDA'),
	CONSTRAINT ck_tipo CHECK(tipo IN('IN', 'OUT'))
);
ALTER TABLE caixa
ADD COLUMN dtmRegistro DATE;

##ESTA TABELA GUARDA OS REGISTROS LIGANDO UMA SAIDA DE DINHEIRO DO CAIXA A UMA ENTRADA DE ITEM NO ESTOQUE
CREATE TABLE compra(
	idCompra INT(11) NOT NULL AUTO_INCREMENT,
	idCaixa INT(11) NOT NULL,
	idLote INT(11) NOT NULL,
	CONSTRAINT pk_compra PRIMARY KEY(idCompra),
	CONSTRAINT fk_caixa_out FOREIGN KEY(idCaixa) REFERENCES caixa(idCaixa),
	CONSTRAINT fk_lote_c FOREIGN KEY(idLote) REFERENCES lote(idLote)
);

##ESTA TABELA GUARDA OS REGISTROS LIGANDO UMA ENTRADA DE DINHEIRO NO CAIXA A UMA SAIDA DE ITEM NO ESTOQUE
CREATE TABLE venda(
	idVenda INT(11) NOT NULL AUTO_INCREMENT,
	idCaixa INT(11) NOT NULL,
	idLote INT(11) NOT NULL,
	CONSTRAINT pk_venda PRIMARY KEY(idVenda),
	CONSTRAINT fk_caixa_in FOREIGN KEY(idCaixa) REFERENCES caixa(idCaixa),
	CONSTRAINT fk_lote_v FOREIGN KEY(idLote) REFERENCES lote(idLote)
);
#############################################################################################################