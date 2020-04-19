############################################Script Principal###################################################
##CRIA A TABELA DE FUNÇÕES DOS FUNCIONARIOS NA EMPRESA
CREATE TABLE funcao(
	idFuncao INT(2) NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(16) NOT NULL,
	CONSTRAINT pk_funcao PRIMARY KEY(idFuncao)
);

##CRIA TABELA ESTABELCIMENTO
CREATE TABLE estabelecimento(
	idEstabelecimento INT(4) NOT NULL AUTO_INCREMENT,
	cnpj VARCHAR(18) NOT NULL,
	nome VARCHAR(25),
	cep VARCHAR(8) NOT NULL,
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
	ddd VARCHAR(3) NOT NULL,
	numero VARCHAR(9) NOT NULL,
	CONSTRAINT pk_estab_telefone PRIMARY KEY(idEstabTelefone),
	CONSTRAINT fk_estab FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento)
);

##CRIA TABELA FUNCIONARIO QUE TAMBÉM SERÁ A TABELA AONDE ESTARÁ O LOGIN
CREATE TABLE funcionario(
	matricula INT(4) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	sobrenome VARCHAR(20) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	numero INT(4) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	bairro VARCHAR(30) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	pais VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL,
	login INT(4),
	senha VARCHAR(40),
	funcao INT(2) NOT NULL,
	estabelecimento INT(4) NOT NULL,
	CONSTRAINT pk_matricula PRIMARY KEY(matricula),
	CONSTRAINT ck_login CHECK(login NULL OR login = matricula),
	CONSTRAINT fk_funcao FOREIGN KEY(funcao) REFERENCES funcao(idfuncao),
	CONSTRAINT fk_estabelecimento FOREIGN KEY(estabelecimento) REFERENCES estabelecimento(idEstabelecimento)
);

##CRIA TABELA QUE ARMAZENA O TELEFONE DOS FUNCIONARIOS
CREATE TABLE funcionario_telefone(
	idFuncTelefone INT(4) NOT NULL AUTO_INCREMENT,
	funcionario INT(4) NOT NULL,
	ddd VARCHAR(3) NOT NULL,
	numero VARCHAR(9) NOT NULL,
	CONSTRAINT pk_func_telefone PRIMARY KEY(idFuncTelefone),
	CONSTRAINT fk_func FOREIGN KEY(funcionario) REFERENCES estabelecimento(matricula)
);

##----------------
##PRECISA CRIAR AS TABELAS REFERENTE AOS PRODUTOS E ESTOQUE
##ESTOQUE
##ENTRADA
##SAÍDA
##LOTES
##SETOR