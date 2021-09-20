CREATE TABLE tb_disciplinas (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	ativo BOOLEAN NOT NULL,
	codigo_curso BIGINT NOT NULL,
	CONSTRAINT fk_cursos FOREIGN KEY(codigo_curso) REFERENCES tb_cursos(codigo)
);


INSERT INTO tb_disciplinas(codigo, descricao, ativo, codigo_curso) VALUES (1,  UPPER (FUN_REMOVE_ACENTOS('Gerencia de Projetos')), true, 1 );
INSERT INTO tb_disciplinas(codigo, descricao, ativo, codigo_curso) VALUES (2,  UPPER (FUN_REMOVE_ACENTOS('Programação IV')), true, 1 );
INSERT INTO tb_disciplinas(codigo, descricao, ativo, codigo_curso) VALUES (3,  UPPER (FUN_REMOVE_ACENTOS('Programação I')), true, 1 );
INSERT INTO tb_disciplinas(codigo, descricao, ativo, codigo_curso) VALUES (4,  UPPER (FUN_REMOVE_ACENTOS('Programação II')), true, 1 );
INSERT INTO tb_disciplinas(codigo, descricao, ativo, codigo_curso) VALUES (5,  UPPER (FUN_REMOVE_ACENTOS('Programação III')), true, 1 );

