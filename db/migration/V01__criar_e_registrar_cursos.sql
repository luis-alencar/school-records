CREATE TABLE tb_cursos (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	localidade VARCHAR(50) NOT NULL,
	status VARCHAR(1) NOT NULL
);


INSERT INTO tb_cursos(codigo, descricao, localidade, status) VALUES (1,  'Sistema de informação', 'Santa Helena de Goiás', 'A' );

SELECT DESCRICAO,
	TO_CHAR(DATA_CADASTRO, 'DD/MM/YYYY') AS DATA_CADASTRO,
	LOCALIDADE,
	STATUS
FROM TB_CURSOS;