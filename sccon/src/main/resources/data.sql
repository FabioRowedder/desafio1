DROP TABLE IF EXISTS PESSOAS;
DROP TABLE IF EXISTS SALARIOS_CONFIG;

CREATE TABLE PESSOAS (
	id INT PRIMARY KEY,
    nome VARCHAR(255),
    dt_nascimento DATE,
    dt_admissao DATE    
);

INSERT INTO PESSOAS (id, nome, dt_nascimento, dt_admissao) VALUES (1, 'José da Silva', '2000-04-06', '2020-05-10');
INSERT INTO PESSOAS (id, nome, dt_nascimento, dt_admissao) VALUES (2, 'Isabel da Silva', '2002-05-15', '2023-04-10');
INSERT INTO PESSOAS (id, nome, dt_nascimento, dt_admissao) VALUES (3, 'André da Silva', '2001-03-02', '2020-10-05');


CREATE TABLE SALARIOS_CONFIG (
	id INT PRIMARY KEY,
	salario_inicial DECIMAL,
	acrescimo_fixo_anual DECIMAL,
	percentual_acrescimo_anual DECIMAL,
	salario_minimo DECIMAL
);

INSERT INTO SALARIOS_CONFIG (id, salario_inicial, acrescimo_fixo_anual, percentual_acrescimo_anual, salario_minimo) VALUES (1, 1558, 500, 18, 1302);
