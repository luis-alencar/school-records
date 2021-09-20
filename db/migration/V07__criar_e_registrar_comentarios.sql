CREATE TABLE tb_comentarios (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	titulo VARCHAR(50) NOT NULL,
	observacao VARCHAR(250),
	nota DECIMAL(10,2) NOT NULL,
	anexo VARCHAR(200),
	codigo_grupo BIGINT NOT NULL,
    CONSTRAINT fk_grupos FOREIGN KEY(codigo_grupo) REFERENCES tb_grupos(codigo)
);


INSERT INTO tb_comentarios(codigo, titulo, observacao, nota, anexo, codigo_grupo)
VALUES (1,  'Atividade de Cadastro ', 'Não foi realizado na data solicitada',0.5,'', 1);


