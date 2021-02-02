CREATE TABLE ESTADOS (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(50) NOT NULL UNIQUE,
	SIGLA VARCHAR(3) NOT NULL UNIQUE
);

CREATE TABLE CIDADES (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(80) NOT NULL UNIQUE,
	ESTADO_ID BIGINT,
	
	FOREIGN KEY (ESTADO_ID) REFERENCES ESTADOS(ID) ON DELETE CASCADE
);

CREATE TABLE CLIENTE (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME_COMPLETO VARCHAR(255) NOT NULL UNIQUE,
	SEXO ENUM('M', 'F') NOT NULL,
	DATA_NASCIMENTO DATE NOT NULL,
	CIDADE_ID BIGINT,
	IS_ADMIN INT DEFAULT 0,
	
	FOREIGN KEY (CIDADE_ID) REFERENCES CIDADES(ID) ON DELETE SET NULL 
);