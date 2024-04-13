CREATE TABLE IF NOT EXISTS andar (
    id           INT UNSIGNED       PRIMARY KEY AUTO_INCREMENT,
    nome         VARCHAR(10)        UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS unidade_administrativa (
    id           INT UNSIGNED       PRIMARY KEY AUTO_INCREMENT,
    sigla        VARCHAR(55)        UNIQUE NOT NULL,
    descricao    VARCHAR(100)       UNIQUE
);

CREATE TABLE IF NOT EXISTS setor (
    id           INT UNSIGNED       PRIMARY KEY AUTO_INCREMENT,
    sigla        VARCHAR(55)        UNIQUE NOT NULL,
    descricao    VARCHAR(100)       UNIQUE NOT NULL,
    fk_unidade_administrativa_id    INT UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS contato (
    id              BIGINT UNSIGNED     PRIMARY KEY AUTO_INCREMENT,
    nome            VARCHAR(55)         UNIQUE NOT NULL,
    numero          VARCHAR(55)                NOT NULL,
    fk_setor_id     INT  UNSIGNED,
    fk_andar_id     INT  UNSIGNED
);

ALTER TABLE setor   ADD FOREIGN KEY (fk_unidade_administrativa_id) REFERENCES unidade_administrativa(id);

ALTER TABLE contato ADD FOREIGN KEY (fk_setor_id) REFERENCES setor(id);
ALTER TABLE contato ADD FOREIGN KEY (fk_andar_id) REFERENCES andar(id);

DESC andar;
DESC unidade_administrativa;
DESC setor;
DESC contato;
