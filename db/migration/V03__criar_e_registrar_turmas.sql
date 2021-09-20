CREATE TABLE tb_turmas (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	ano INT NOT NULL,
	semestre VARCHAR(2) NOT NULL,
	status VARCHAR(1) NOT NULL,
	codigo_disciplinas BIGINT NOT NULL,
    CONSTRAINT fk_disciplinas FOREIGN KEY(codigo_disciplinas) REFERENCES tb_disciplinas(codigo)
);


INSERT INTO tb_turmas(codigo, descricao, ano, semestre, status, codigo_disciplinas) VALUES (1,  'Primeiro Período', 2021,'1', 'A', 1 );

SELECT
	T.CODIGO,
	T.DESCRICAO,
	TO_CHAR(T.DATA_CADASTRO, 'DD/MM/YYYY') AS DATA_CADASTRO,
	T.STATUS,
	D.DESCRICAO AS DESCRICAO_CURSO
FROM TB_DISCIPLINAS D, TB_CURSOS C, TB_TURMAS T
WHERE D.CODIGO_CURSOS = C.CODIGO
	AND D.CODIGO = T.CODIGO_DISCIPLINAS;