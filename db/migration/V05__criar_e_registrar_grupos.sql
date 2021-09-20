CREATE TABLE tb_grupos (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	nome_empresa VARCHAR(50) NOT NULL,
	nome_produto VARCHAR(50) NOT NULL,
	tema_projeto VARCHAR(50) NOT NULL,
	observacao VARCHAR(250),
	status VARCHAR(1) NOT NULL
);


INSERT INTO tb_grupos(codigo, nome_empresa, nome_produto, tema_projeto, observacao, status)
VALUES (1,  'Start Computer', 'School Records','Start Computer','Desenvolver um sistema de gestão de grupos como um prontuario', 'A');


