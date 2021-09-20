ALTER TABLE tb_alunos ADD COLUMN  codigo_grupo  BIGINT;

ALTER TABLE tb_alunos ALTER COLUMN codigo_grupo SET NOT NULL;

ALTER TABLE tb_alunos ADD CONSTRAINT fk_grupos FOREIGN KEY(codigo_grupo) REFERENCES tb_grupos(codigo);

UPDATE tb_alunos SET codigo_grupo = 1;