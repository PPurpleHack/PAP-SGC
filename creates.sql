##CRIAÇÃO TABELA USUARIO
CREATE TABLE usuario(
	matricula INT(8) NOT NULL,
	senha VARCHAR(50) NOT NULL,
	nome VARCHAR(25) NOT NULL,
	email VARCHAR(50) NOT NULL,
	tipo_usuario INT(3) NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY(matricula)
);

##CRIAÇÃO DA TABELA TIPO USUARIO
CREATE TABLE tipo_usuario(
	id INT(3) NOT NULL AUTO_INCREMENT,
	descricao_curta VARCHAR(30) NOT NULL,
	descricao_longa VARCHAR(200),
	CONSTRAINT pk_tipo_usuario PRIMARY KEY(id)
);


##INSERÇÃO DO TIPO USUARIO MASTER
INSERT INTO tipo_usuario(descricao_curta, descricao_longa)
VALUES('MASTER', 'Usuario master do sistema com todas as permissões');

##VINCULO DAS TABELAS USUARIO E TIPO_USUARIO
ALTER TABLE usuario
ADD CONSTRAINT fk_tipo_usuario FOREIGN KEY(tipo_usuario) REFERENCES tipo_usuario(id);

###############################################################################################################
##OQUE TEM PRA FAZER
##CRIAR TABELA PARA OS ESTABELECIMENTOS
##CRIAR TABELA ESTOQUE GRUPO
##CRIAR TABELA ESTOQUE ITEM
##CRIAR TABELA ESTOQUE GRUPO ITEM
##CRIAR TABELA CATEGORIAS ITEM
##CRIAR TABELA PARA O HISTORICO DE COMPRAS
##CRIAR TABELA PARA O HISTORICO DE SAIDAS
##CRIAR TABELA AUXILIAR PRA TIPOS DE SAIDAS