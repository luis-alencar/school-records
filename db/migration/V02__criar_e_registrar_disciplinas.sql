CREATE TABLE tb_disciplinas (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	status VARCHAR(1) NOT NULL,
	codigo_cursos BIGINT NOT NULL,
	CONSTRAINT fk_cursos FOREIGN KEY(codigo_cursos) REFERENCES tb_cursos(codigo)
);


INSERT INTO tb_disciplinas(codigo, descricao, status, codigo_cursos) VALUES (1,  'Gerencia de Projetos', 'A', 1 );

SELECT
	D.CODIGO,
	D.DESCRICAO,
	TO_CHAR(D.DATA_CADASTRO, 'DD/MM/YYYY') AS DATA_CADASTRO,
	D.STATUS,
	C.DESCRICAO AS DESCRICAO_CURSO
FROM TB_DISCIPLINAS D, TB_CURSOS C
WHERE D.CODIGO_CURSOS = C.CODIGO;