CREATE TABLE IF NOT EXISTS andar (
    id           TINYINT UNSIGNED   PRIMARY KEY AUTO_INCREMENT,
    nome         VARCHAR(10)        UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS unidade_administrativa (
    id           SMALLINT UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
    sigla        VARCHAR(55)        UNIQUE NOT NULL,
    descricao    VARCHAR(100)       UNIQUE
);

CREATE TABLE IF NOT EXISTS setor (
    id           SMALLINT UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
    sigla        VARCHAR(55)        UNIQUE NOT NULL,
    descricao    VARCHAR(100)       UNIQUE NOT NULL,
    fk_unidade_administrativa_id    SMALLINT UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS contato (
    id              SMALLINT UNSIGNED   PRIMARY KEY AUTO_INCREMENT,
    nome            VARCHAR(55)         UNIQUE NOT NULL,
    numero          VARCHAR(55)                NOT NULL,
    fk_setor_id     SMALLINT UNSIGNED,
    fk_andar_id     TINYINT  UNSIGNED
);

ALTER TABLE setor   ADD FOREIGN KEY (fk_unidade_administrativa_id) REFERENCES unidade_administrativa(id);

ALTER TABLE contato ADD FOREIGN KEY (fk_setor_id) REFERENCES setor(id);
ALTER TABLE contato ADD FOREIGN KEY (fk_andar_id) REFERENCES andar(id);

