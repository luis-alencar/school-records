CREATE TABLE tb_cursos (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	localidade VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL
);


INSERT INTO tb_cursos(codigo, descricao, localidade, ativo) VALUES (1,  FUN_REMOVE_ACENTOS('Sistema de informação'), FUN_REMOVE_ACENTOS('Santa Helena de Goiás'), true );
INSERT INTO tb_cursos(codigo, descricao, localidade, ativo) VALUES (2,  FUN_REMOVE_ACENTOS('Matematica'), FUN_REMOVE_ACENTOS('Santa Helena de Goiás'), true );
INSERT INTO tb_cursos(codigo, descricao, localidade, ativo) VALUES (3,  FUN_REMOVE_ACENTOS('Engenharia Agrícola'), FUN_REMOVE_ACENTOS('Santa Helena de Goiás'), true );
INSERT INTO tb_cursos(codigo, descricao, localidade, ativo) VALUES (4,  FUN_REMOVE_ACENTOS('Administração'), FUN_REMOVE_ACENTOS('Santa Helena de Goiás'), true );



--
-- SELECT DESCRICAO,
-- 	TO_CHAR(DATA_CADASTRO, 'DD/MM/YYYY') AS DATA_CADASTRO,
-- 	FUN_REMOVE_ACENTOS(LOCALIDADE),
-- 	STATUS,
-- 	CASE STATUS WHEN true THEN 'ATIVO' WHEN false THEN 'CANCELADO' END AS  STATUS_CURSOS
-- FROM TB_CURSOS;
